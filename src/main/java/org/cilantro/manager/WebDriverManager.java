package org.cilantro.manager;

import static org.cilantro.enums.Message.BROWSER_OPTION_NOT_SUPPORTED;
import static org.cilantro.enums.Message.CAPABILITIES_REQUIRED_FOR_REMOTE;
import static org.cilantro.enums.Message.EMPTY_BROWSER_NOT_ALLOWED;
import static org.cilantro.enums.Message.HOSTNAME_REQUIRED_FOR_REMOTE;
import static org.cilantro.enums.Message.INVALID_BROWSER;
import static org.cilantro.enums.Message.INVALID_REMOTE_URL;
import static org.cilantro.enums.Message.NULL_REMOTE_URL;
import static org.cilantro.enums.Message.PAGE_LOAD_STRATEGY_MISSING;
import static org.cilantro.enums.Message.PASSWORD_REQUIRED_FOR_CLOUD;
import static org.cilantro.enums.Message.PROTOCOL_REQUIRED_FOR_HOST;
import static org.cilantro.enums.Message.SESSION_NOT_STARTED;
import static org.cilantro.enums.Message.USER_NAME_REQUIRED_FOR_CLOUD;
import static org.cilantro.enums.TargetProviders.LOCAL;
import static org.cilantro.manager.ParallelSession.getSession;
import static org.cilantro.manager.ParallelSession.setDriver;
import static org.cilantro.utils.ErrorHandler.handleAndThrow;
import static org.cilantro.utils.ErrorHandler.throwError;
import static org.cilantro.utils.Validator.requireNonNull;
import static java.text.MessageFormat.format;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNullElse;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.cilantro.actions.drivers.NavigateActions;
import org.cilantro.config.ui.web.WebSetting;
import org.cilantro.enums.Browser;
import org.cilantro.enums.TargetProviders;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

class WebDriverManager implements IDriverManager {
    private static final String HEADLESS = "--headless=new";
    private static final Logger LOGGER   = getLogger ();

    @Override
    public void setupDriver () {
        LOGGER.traceEntry ();
        final var webSetting = getSession ().getWebSetting ();
        try {
            switch (requireNonNull (webSetting.getBrowser (), EMPTY_BROWSER_NOT_ALLOWED)) {
                case CHROME -> setDriver (setupChromeDriver (webSetting));
                case NONE -> throwError (INVALID_BROWSER);
                case REMOTE -> setDriver (setupRemoteDriver (webSetting));
                case SAFARI -> setDriver (setupSafariDriver (webSetting));
                case EDGE -> setDriver (setupEdgeDriver (webSetting));
                default -> setDriver (setupFirefoxDriver (webSetting));
            }
        } catch (final SessionNotCreatedException e) {
            handleAndThrow (SESSION_NOT_STARTED, e);
        }
        setDriverSize (webSetting);
        navigateToBaseUrl (webSetting.getBaseUrl ());
        LOGGER.traceExit ();
    }

    private MutableCapabilities getBrowserOptions (final Browser browser, final WebSetting webSetting) {
        MutableCapabilities capabilities = null;
        switch (browser) {
            case CHROME -> capabilities = getChromeOptions (webSetting);
            case EDGE -> capabilities = getEdgeOptions (webSetting);
            case FIREFOX -> capabilities = getFirefoxOptions (webSetting);
            case SAFARI -> capabilities = getSafariOptions (webSetting);
            default -> throwError (BROWSER_OPTION_NOT_SUPPORTED, browser.name ());
        }
        return capabilities;
    }

    private Capabilities getCapabilities (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        final var capabilities = requireNonNull (webSetting.getCapabilities (), CAPABILITIES_REQUIRED_FOR_REMOTE);
        final var remoteCapabilities = new MutableCapabilities ();
        setupCloudDriverOptions (remoteCapabilities, capabilities, webSetting.getTarget ());
        setupDriverOptions (remoteCapabilities, capabilities, webSetting);
        return LOGGER.traceExit (remoteCapabilities);
    }

    private ChromeOptions getChromeOptions (final WebSetting webSetting) {
        final var options = new ChromeOptions ();
        setCommonBrowserOptions (options, webSetting);
        return options;
    }

    private EdgeOptions getEdgeOptions (final WebSetting webSetting) {
        final var options = new EdgeOptions ();
        setCommonBrowserOptions (options, webSetting);
        return options;
    }

    private FirefoxOptions getFirefoxOptions (final WebSetting webSetting) {
        final var options = new FirefoxOptions ();
        ofNullable (webSetting.getPlatform ()).ifPresent (options::setPlatformName);
        ofNullable (webSetting.getBrowserOptions ()).ifPresent (l -> l.forEach (options::addArguments));
        if (webSetting.getTarget () == LOCAL && isNull (webSetting.getCapabilities ())) {
            options.setBrowserVersion (webSetting.getVersion ());
        }
        options.setPageLoadStrategy (requireNonNull (webSetting.getPageLoadStrategy (), PAGE_LOAD_STRATEGY_MISSING));
        if (webSetting.isHeadless ()) {
            options.addArguments (HEADLESS);
        }
        return options;
    }

    private String getHostName (final WebSetting webSetting, final TargetProviders target) {
        LOGGER.traceEntry ();
        final var host = requireNonNullElse (webSetting.getHost (),
            requireNonNull (target, HOSTNAME_REQUIRED_FOR_REMOTE).getHost ());
        if (requireNonNullElse (webSetting.getTarget (), LOCAL) != LOCAL) {
            final var hostNamePattern = "{0}:{1}@{2}";
            return format (hostNamePattern, requireNonNull (webSetting.getUserName (), USER_NAME_REQUIRED_FOR_CLOUD),
                requireNonNull (webSetting.getPassword (), PASSWORD_REQUIRED_FOR_CLOUD), host);
        }
        return LOGGER.traceExit (host);
    }

    private URL getRemoteUrl (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        final var URL_PATTERN = "{0}://{1}";
        final var target = webSetting.getTarget ();
        final var hostName = new StringBuilder (getHostName (webSetting, target));
        if (webSetting.getPort () != 0) {
            hostName.append (":")
                .append (webSetting.getPort ());
        }
        if (target != LOCAL) {
            hostName.append ("/wd/hub");
        }
        final var url = format (URL_PATTERN,
            requireNonNull (requireNonNullElse (webSetting.getProtocol (), target.getProtocol ()),
                PROTOCOL_REQUIRED_FOR_HOST, hostName).name ()
                .toLowerCase (), hostName);
        try {
            return LOGGER.traceExit (new URL (url));
        } catch (final MalformedURLException e) {
            handleAndThrow (INVALID_REMOTE_URL, e);
        }
        return null;
    }

    private SafariOptions getSafariOptions (final WebSetting webSetting) {
        final var options = new SafariOptions ();
        if (webSetting.getTarget () == LOCAL && isNull (webSetting.getCapabilities ())) {
            options.setBrowserVersion (webSetting.getVersion ());
        }
        options.setPageLoadStrategy (requireNonNull (webSetting.getPageLoadStrategy (), PAGE_LOAD_STRATEGY_MISSING));
        ofNullable (webSetting.getPlatform ()).ifPresent (options::setPlatformName);
        return options;
    }

    private void navigateToBaseUrl (final String baseUrl) {
        if (isNotEmpty (baseUrl)) {
            NavigateActions.navigate ()
                .to (baseUrl);
        }
    }

    private <T extends ChromiumOptions<T>> void setCommonBrowserOptions (final T options, final WebSetting webSetting) {
        ofNullable (webSetting.getPlatform ()).ifPresent (options::setPlatformName);
        if (webSetting.getTarget () == LOCAL && isNull (webSetting.getCapabilities ())) {
            options.setBrowserVersion (webSetting.getVersion ());
        }
        options.setPageLoadStrategy (requireNonNull (webSetting.getPageLoadStrategy (), PAGE_LOAD_STRATEGY_MISSING));
        options.addArguments ("enable-automation");
        options.addArguments ("--no-sandbox");
        options.addArguments ("--disable-gpu");
        options.addArguments ("--disable-dev-shm-usage");
        options.addArguments("--disable-blink-features=AutomationControlled");
//        options.addArguments("--disable-infobars");
//        options.addArguments("--disable-extensions") ;
        ofNullable (webSetting.getBrowserOptions ()).ifPresent (l -> l.forEach (options::addArguments));
        if (webSetting.isHeadless ()) {
            options.addArguments (HEADLESS);
        }
    }

    private void setDriverSize (final WebSetting webSetting) {
        final var window = getSession ().getDriver ()
            .manage ()
            .window ();
        switch (webSetting.getResize ()) {
            case CUSTOM:
                window.setSize (webSetting.getCustomSize ());
                break;
            case FULL_SCREEN:
                window.fullscreen ();
                break;
            case MAXIMIZED:
                window.maximize ();
                break;
            case MINIMIZED:
                window.minimize ();
                break;
            default:
                break;
        }
    }

    private WebDriver setupChromeDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        final var options = getChromeOptions (webSetting);
        return LOGGER.traceExit (new ChromeDriver (options));
    }

    private <E extends MutableCapabilities> void setupDriverOptions (final E options,
        final Map<String, Object> capabilities, final WebSetting webSetting) {
        if (!isNull (capabilities) && webSetting.getTarget () == LOCAL) {
            final var browserName = capabilities.get ("browserName")
                .toString ();
            if (isNotEmpty (browserName)) {
                requireNonNull (getBrowserOptions (Browser.valueOf (browserName.toUpperCase ()), webSetting),
                    BROWSER_OPTION_NOT_SUPPORTED, browserName).asMap ()
                    .forEach (options::setCapability);
            }
            capabilities.forEach (options::setCapability);
        }
    }

    private WebDriver setupEdgeDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        final var options = getEdgeOptions (webSetting);
        return LOGGER.traceExit (new EdgeDriver (options));
    }

    private WebDriver setupFirefoxDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        final var options = getFirefoxOptions (webSetting);
        return LOGGER.traceExit (new FirefoxDriver (options));
    }

    private WebDriver setupRemoteDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        final var driver = new RemoteWebDriver (requireNonNull (getRemoteUrl (webSetting), NULL_REMOTE_URL),
            getCapabilities (webSetting));
        driver.setFileDetector (new LocalFileDetector ());
        return LOGGER.traceExit (driver);
    }

    private WebDriver setupSafariDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        final var options = getSafariOptions (webSetting);
        return LOGGER.traceExit (new SafariDriver (options));
    }
}
