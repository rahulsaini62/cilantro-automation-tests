package org.cilantro.actions;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Sequence;
import org.cilantro.builders.Locator;
import org.cilantro.exception.FrameworkError;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.text.MessageFormat.format;
import static java.time.Duration.ofMillis;
import static java.util.Objects.isNull;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.cilantro.actions.drivers.DriverActions.withDriver;
import static org.cilantro.actions.elements.ElementFinder.find;
import static org.cilantro.enums.Message.DRIVER_ERROR_OCCURRED;
import static org.cilantro.enums.PlatformType.WEB;
import static org.cilantro.enums.WaitStrategy.CLICKABLE;
import static org.cilantro.enums.WaitStrategy.VISIBLE;
import static org.cilantro.manager.ParallelSession.getSession;
import static org.cilantro.utils.ErrorHandler.handleAndThrow;
import static org.cilantro.utils.Validator.validateDelay;

/**
 * Common action methods to perform different actions on devices / browsers and on elements.
 *
 * @author Rahul Saini
 * @since 25-Dec-2024
 */
@SuppressWarnings("unchecked")
public final class CommonActions {
    private static final String HIGHLIGHT_STYLE = "highlight.style";
    private static final Logger LOGGER = getLogger();

    /**
     * Gets driver specific attributes.
     *
     * @param action       action to get driver specific attributes
     * @param defaultValue default value if any error occurred
     * @param <D>          driver type
     * @param <E>          attribute type
     * @return driver specific attribute.
     */
    public static <D extends WebDriver, E> E getDriverAttribute(final Function<D, E> action, final E defaultValue) {
        LOGGER.traceEntry();
        try {
            return LOGGER.traceExit(action.apply((D) getSession().getDriver()));
        } catch (final FrameworkError e) {
            if (!isNull(defaultValue)) {
                return defaultValue;
            } else {
                throw e;
            }
        }
    }

    /**
     * Gets element specific attributes.
     *
     * @param action       action to get element specific attributes
     * @param locator      locator to find element
     * @param defaultValue default value if any error occurred
     * @param <E>          attribute type
     * @return element specific attribute.
     */
    public static <E> E getElementAttribute(final Function<WebElement, E> action, final Locator locator,
                                            final E defaultValue) {
        LOGGER.traceEntry();
        try {
            prepareElementAction(find(locator, VISIBLE), "green");
            return LOGGER.traceExit(action.apply(find(locator, VISIBLE)));
        } catch (final FrameworkError e) {
            return defaultValue;
        }
    }

    public static <E> E getElementAttributeWithoutWait(final Function<WebElement, E> action, final Locator locator,
                                                       final E defaultValue) {
        LOGGER.traceEntry();
        try {
            prepareElementAction(find(locator), "green");
            return LOGGER.traceExit(action.apply(find(locator)));
        } catch (final FrameworkError e) {
            return defaultValue;
        }
    }

    /**
     * Gets element specific attributes.
     *
     * @param action       action to get element specific attributes
     * @param element      locator to find element
     * @param defaultValue default value if any error occurred
     * @param <E>          attribute type
     * @return element specific attribute.
     */
    public static <E> E getElementAttribute(final Function<WebElement, E> action, final WebElement element,
                                            final E defaultValue) {
        LOGGER.traceEntry();
        try {
            prepareElementAction(element, "green");
            return LOGGER.traceExit(action.apply(element));
        } catch (final FrameworkError e) {
            return defaultValue;
        }
    }

    /**
     * Pause till the specified delay.
     *
     * @param delayMillis Delay till this amount millis
     */
    public static void pause(final int delayMillis) {
        validateDelay(delayMillis);
        withDriver().pause(ofMillis(delayMillis));
    }

    /**
     * Perform driver specific action.
     *
     * @param action action to perform
     * @param <D>    driver type
     */
    public static <D extends WebDriver> void performDriverAction(final Consumer<D> action) {
        LOGGER.traceEntry();
        try {
            action.accept((D) getSession().getDriver());
        } catch (final WebDriverException e) {
            handleAndThrow(DRIVER_ERROR_OCCURRED, e, e.getMessage());
        }
        LOGGER.traceExit();
    }

    /**
     * Perform element specific action with Driver.
     *
     * @param action  action to perform
     * @param locator locator to find element
     * @param <D>     Type of Driver
     */
    public static <D extends WebDriver> void performElementAction(final BiConsumer<D, WebElement> action,
                                                                  final Locator locator) {
        LOGGER.traceEntry();
        try {
            prepareElementAction(find(locator, CLICKABLE), "red");
            action.accept((D) getSession().getDriver(), find(locator, CLICKABLE));
        } catch (final WebDriverException e) {
            handleAndThrow(DRIVER_ERROR_OCCURRED, e, e.getMessage());
        }
        LOGGER.traceExit();
    }

    /**
     * Perform element specific action with Driver.
     *
     * @param action  action to perform
     * @param element locator to find element
     * @param <D>     Type of Driver
     */
    public static <D extends WebDriver> void performElementAction(final BiConsumer<D, WebElement> action,
                                                                  final WebElement element) {
        LOGGER.traceEntry();
        try {
            prepareElementAction(element, "red");
            action.accept((D) getSession().getDriver(), element);
        } catch (final WebDriverException e) {
            handleAndThrow(DRIVER_ERROR_OCCURRED, e, e.getMessage());
        }
        LOGGER.traceExit();
    }

    /**
     * Perform element specific action.
     *
     * @param action  action to perform
     * @param locator locator to find element
     */
    public static void performElementAction(final Consumer<WebElement> action, final Locator locator) {
        LOGGER.traceEntry();
        try {
            prepareElementAction(find(locator, VISIBLE), "red");
            action.accept(find(locator, VISIBLE));
        } catch (final WebDriverException e) {
            handleAndThrow(DRIVER_ERROR_OCCURRED, e, e.getMessage());
        }
        LOGGER.traceExit();
    }

    /**
     * Perform element specific action.
     *
     * @param action  action to perform
     * @param element locator to find element
     */
    public static void performElementAction(final Consumer<WebElement> action, final WebElement element) {
        LOGGER.traceEntry();
        try {
            prepareElementAction(element, "red");
            action.accept(element);
        } catch (final WebDriverException e) {
            handleAndThrow(DRIVER_ERROR_OCCURRED, e, e.getMessage());
        }
        LOGGER.traceExit();
    }

    /**
     * Perform Gestures on Mobile.
     *
     * @param sequences Collection of Sequences of gestures.
     */
    public static void performMobileGestures(final Collection<Sequence> sequences) {
        LOGGER.traceEntry();
        try {
            performDriverAction(driver -> ((AppiumDriver) driver).perform(sequences));
        } catch (final WebDriverException e) {
            handleAndThrow(DRIVER_ERROR_OCCURRED, e, e.getMessage());
        }
        LOGGER.traceExit();
    }

    private static void highlight(final String color, final WebElement element) {
        if (getSession().getWebSetting()
                .isHighlight()) {
            final var style = element.getAttribute("style");
            getSession().setSharedData(HIGHLIGHT_STYLE, style);
            withDriver().executeScript("arguments[0].setAttribute('style', arguments[1] + arguments[2]);", element,
                    style, format("color: {0}; border: 3px solid {0};", color));
            pause(getSession().getSetting()
                    .getUi()
                    .getTimeout()
                    .getHighlightDelay());
        }
    }

    private static void prepareElementAction(final WebElement element, final String color) {
        if (getSession().getPlatformType() == WEB) {
            highlight(color, element);
            unhighlight(element);
        }
    }

    private static void unhighlight(final WebElement element) {
        if (getSession().getWebSetting()
                .isHighlight()) {
            final var style = getSession().getSharedData(HIGHLIGHT_STYLE);
            withDriver().executeScript("arguments[0].setAttribute('style', arguments[1]);", element, style);
            getSession().removeSharedData(HIGHLIGHT_STYLE);
        }
    }

    public static void sleep(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (final InterruptedException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    private CommonActions() {
        // Utility class
    }

    public static void setAttribute(final Locator element, String attributeName, String attributeValue) {
        if (getSession().getWebSetting()
                .isHighlight()) {
            final var style = getSession().getSharedData(HIGHLIGHT_STYLE);
            withDriver().executeScript("arguments[0].setAttribute('" + attributeName + "', " + attributeValue + ");", element);
            getSession().removeSharedData(HIGHLIGHT_STYLE);
        }
    }

    public static void useKeys(Keys key) {

        performDriverAction(driver -> {

            Actions actions = new Actions(driver);
            actions.sendKeys(key).perform();
            sleep(1000);
        });
    }
}

