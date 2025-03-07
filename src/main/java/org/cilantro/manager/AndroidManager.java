package org.cilantro.manager;

import static io.appium.java_client.Setting.IGNORE_UNIMPORTANT_VIEWS;
import static org.cilantro.enums.AutomationType.UI_AUTOMATOR;
import static org.cilantro.enums.DeviceType.VIRTUAL;
import static java.time.Duration.ofSeconds;
import static java.util.Objects.isNull;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.cilantro.config.ui.mobile.MobileSetting;
import org.cilantro.config.ui.mobile.device.ApplicationSetting;
import org.cilantro.config.ui.mobile.device.DeviceSetting;
import org.cilantro.config.ui.mobile.device.VirtualDeviceSetting;
import org.cilantro.enums.ApplicationType;
import org.cilantro.enums.DeviceType;
import org.cilantro.enums.Message;
import org.cilantro.enums.TargetProviders;
import org.cilantro.utils.ErrorHandler;
import org.cilantro.utils.Validator;
import org.openqa.selenium.SessionNotCreatedException;

class AndroidManager implements IDriverManager {
    private final MobileSetting mobileSetting;
    private final DeviceSetting settings;

    public AndroidManager () {
        this.mobileSetting = ParallelSession.getSession ()
            .getMobileSetting ();
        this.settings = this.mobileSetting.getDevice ();
    }

    @Override
    public void setupDriver () {
        final var automation = this.mobileSetting.getServer ()
            .getDriver ();
        if (automation == UI_AUTOMATOR) {
            setupUiAutomatorDriver (this.mobileSetting.getServer ()
                .getTarget ());
        }
        setupAndroidSettings ();
    }

    private void setAndroidApplicationOptions (final UiAutomator2Options options,
        final ApplicationSetting application) {
        if (application.getType () == ApplicationType.WEB) {
            options.withBrowserName (application.getBrowser ()
                .name ());
            Validator.setOptionIfPresent (application.getChromeDriverPort (), options::setChromedriverPort);
        } else {
            setupApplicationOptions (application, options);
            options.setAppWaitActivity (application.getWaitActivity ());
            options.setAppWaitDuration (ofSeconds (application.getWaitTimeout ()));
            options.setAndroidInstallTimeout (ofSeconds (application.getInstallTimeout ()));
        }
    }

    private void setAvdOptions (final UiAutomator2Options options, final DeviceType type,
        final VirtualDeviceSetting avd) {
        if (type == VIRTUAL && !isNull (avd)) {
            options.setAvd (avd.getName ());
            options.setIsHeadless (avd.isHeadless ());
            options.setAvdLaunchTimeout (ofSeconds (avd.getLaunchTimeout ()));
            options.setAvdReadyTimeout (ofSeconds (avd.getReadyTimeout ()));
        }
    }

    private void setCommonUiAutomatorOptions (final UiAutomator2Options options) {
        setAndroidApplicationOptions (options, this.settings.getApplication ());
        options.setAutomationName (this.mobileSetting.getServer ()
            .getDriver ()
            .getName ());
        options.setPlatformName (this.settings.getOs ()
            .name ());
        options.setPlatformVersion (this.settings.getVersion ());
        options.setDeviceName (this.settings.getName ());
    }

    private void setLocalUiAutomatorOptions (final UiAutomator2Options options) {
        setAvdOptions (options, this.settings.getType (), this.settings.getVirtualDevice ());
        options.setClearSystemFiles (this.settings.isClearFiles ());
        options.setClearDeviceLogsOnStart (this.settings.isClearLogs ());
        options.setNoReset (this.settings.isNoReset ());
        options.setFullReset (this.settings.isFullReset ());
        options.setUiautomator2ServerLaunchTimeout (ofSeconds (this.settings.getServerLaunchTimeout ()));
        options.setUiautomator2ServerInstallTimeout (ofSeconds (this.settings.getServerInstallTimeout ()));
        options.setAdbExecTimeout (ofSeconds (this.settings.getAdbTimeout ()));
        options.setSystemPort (this.settings.getSystemPort ());
        options.setUdid (this.settings.getUniqueId ());
    }

    private void setupAndroidSettings () {
        final var driver = (AndroidDriver) ParallelSession.getSession ()
            .getDriver ();
        driver.setSetting (IGNORE_UNIMPORTANT_VIEWS, this.settings.isIgnoreUnimportantViews ());
    }

    private void setupUiAutomatorDriver (final TargetProviders targetProviders) {
        final var options = new UiAutomator2Options ();
        setCommonUiAutomatorOptions (options);
        if (this.settings.getType () == DeviceType.CLOUD) {
            setupCloudDriverOptions (options, this.settings.getCapabilities (), targetProviders);
        } else {
            setLocalUiAutomatorOptions (options);
        }
        try {
            ParallelSession.setDriver (new AndroidDriver (ParallelSession.getSession ()
                .getServiceManager ()
                .getServiceUrl (), options));
        } catch (final SessionNotCreatedException e) {
            ErrorHandler.handleAndThrow (Message.SESSION_NOT_STARTED, e);
        }
        navigateToBaseUrl (this.settings.getApplication ());
    }
}
