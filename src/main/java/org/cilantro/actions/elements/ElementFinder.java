package org.cilantro.actions.elements;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.cilantro.builders.Locator;
import org.cilantro.enums.WaitStrategy;
import org.cilantro.exception.FrameworkError;

import java.util.List;

import static java.text.MessageFormat.format;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.cilantro.enums.Message.BASE_URL_EMPTY;
import static org.cilantro.enums.Message.ELEMENT_NOT_FOUND;
import static org.cilantro.manager.ParallelSession.getSession;
import static org.cilantro.utils.ErrorHandler.handleAndThrow;
import static org.cilantro.utils.ErrorHandler.throwError;

/**
 * Finds element on UI.
 *
 * @author Rahul Saini
 * @since 24-Dec-2024
 */
public final class ElementFinder {
    private static final Logger LOGGER = getLogger ();

    /**
     * Find single element on UI.
     *
     * @param locator {@link Locator} to find element
     * @param waitStrategy {@link WaitStrategy} for finding the element
     *
     * @return {@link WebElement}
     */
    public static WebElement find (final Locator locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry ();
        final var elements = finds (locator, waitStrategy);
        if (elements.isEmpty ()) {
            throwError (ELEMENT_NOT_FOUND, locator.getName (), getSession ().getPlatformType ());
        }
        if (!isNull (locator.getFilter ())) {
            return elements.stream ()
                .filter (locator.getFilter ())
                .findFirst ()
                .orElseThrow (() -> new FrameworkError (format (ELEMENT_NOT_FOUND.getMessageText (), locator.getName (),
                    getSession ().getPlatformType ())));
        }
        return elements.get (locator.getIndex ());
    }

    /**
     * Find single element on UI.
     *
     * @param locator {@link Locator} to find element
     *
     * @return {@link WebElement}
     */
    public static WebElement find (final Locator locator) {
        LOGGER.traceEntry ();
        final var elements = finds (locator);
        if (elements.isEmpty ()) {
            throwError (ELEMENT_NOT_FOUND, locator.getName (), getSession ().getPlatformType ());
        }
        if (!isNull (locator.getFilter ())) {
            return elements.stream ()
                .filter (locator.getFilter ())
                .findFirst ()
                .orElseThrow (() -> new FrameworkError (format (ELEMENT_NOT_FOUND.getMessageText (), locator.getName (),
                    getSession ().getPlatformType ())));
        }
        return elements.get (locator.getIndex ());
    }

    /**
     * Find all elements on UI.
     *
     * @param locator {@link Locator} to find elements
     *
     * @return {@link List} of {@link WebElement}
     */
    public static List<WebElement> finds (final Locator locator) {
        LOGGER.traceEntry ();
        final var driver = getSession ().getDriver ();
        final List<WebElement> elements;
        if (!isNull (locator.getParent ())) {
            final var parent = find (locator.getParent ());
            elements = finds (driver, parent, locator);
        } else {
            elements = finds (driver, locator);
        }
        return LOGGER.traceExit (elements);
    }

    /**
     * Find all elements on UI.
     *
     * @param locator {@link Locator} to find elements
     * @param waitStrategy {@link WaitStrategy} for finding the element
     *
     * @return {@link List} of {@link WebElement}
     */
    public static List<WebElement> finds (final Locator locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry ();
        final var driver = getSession ().getDriver ();
        final List<WebElement> elements;
        if (!isNull (locator.getParent ())) {
            final var parent = find (locator.getParent (), waitStrategy);
            elements = finds (driver, parent, locator);
        } else {
            waitForElement (locator, waitStrategy);
            elements = finds (driver, locator);
        }
        return LOGGER.traceExit (elements);
    }

    private static <D extends WebDriver> List<WebElement> finds (final D driver, final WebElement parent,
        final Locator locator) {
        LOGGER.traceEntry ();
        final var platformLocator = locator.getLocator ();
        if (isNull (platformLocator)) {
            throwError (ELEMENT_NOT_FOUND, locator.getName (), getSession ().getPlatformType ());
        }
        return LOGGER.traceExit (!isNull (parent)
                                 ? parent.findElements (locator.getLocator ())
                                 : driver.findElements (locator.getLocator ()));
    }

    private static <D extends WebDriver> List<WebElement> finds (final D driver, final Locator locator) {
        LOGGER.traceEntry ();
        return LOGGER.traceExit (finds (driver, null, locator));
    }

    private static void waitForElement (final Locator locator, final WaitStrategy waitStrategy) {
        try {
            final var wait = getSession ().getWait ();
            if (requireNonNull (waitStrategy, "Wait Strategy is null") == WaitStrategy.CLICKABLE) {
                wait.until (elementToBeClickable (locator.getLocator ()));
            } else {
                wait.until (visibilityOfElementLocated (locator.getLocator ()));
            }
        } catch (final TimeoutException e) {
            handleAndThrow (ELEMENT_NOT_FOUND, e, locator.getName (), getSession ().getPlatformType ());
        }
    }

    public static void waitForElementVisible (final Locator locator) {
        try {
            final var wait = getSession ().getWait ();
            wait.until (visibilityOfElementLocated (locator.getLocator ()));
        } catch (final TimeoutException e) {
            handleAndThrow (ELEMENT_NOT_FOUND, e, locator.getName (), getSession ().getPlatformType ());
        }
    }

    public static void waitForElementInvisibility (final Locator locator) {
        try {
            final var wait = getSession ().getWait ();
            wait.until (invisibilityOfElementLocated (locator.getLocator ()));
        } catch (final TimeoutException e) {
            handleAndThrow (ELEMENT_NOT_FOUND, e, locator.getName (), getSession ().getPlatformType ());
        }
    }

    public static void waitForElementClickable (final Locator locator) {
        try {
            final var wait = getSession ().getWait ();
            wait.until (elementToBeClickable (locator.getLocator ()));
        } catch (final TimeoutException e) {
            handleAndThrow (ELEMENT_NOT_FOUND, e, locator.getName (), getSession ().getPlatformType ());
        }
    }

    public static void waitForURLContains (String url) {
        try {
            final var wait = getSession ().getWait ();
            wait.until (urlContains (url));
        } catch (final TimeoutException e) {
            handleAndThrow (BASE_URL_EMPTY, e, getSession ().getPlatformType ());
        }

    }

    private ElementFinder () {
        // Intentionally left blank
    }

}
