package org.cilantro.actions.elements;

import static org.cilantro.actions.CommonActions.pause;
import static org.cilantro.actions.CommonActions.performElementAction;
import static org.cilantro.actions.drivers.DriverActions.withDriver;
import static org.cilantro.actions.elements.ElementFinder.find;
import static org.cilantro.enums.ApplicationType.WEB;
import static org.cilantro.enums.ListenerType.CLICKABLE_ACTION;
import static org.cilantro.enums.PlatformType.IOS;
import static org.cilantro.enums.WaitStrategy.CLICKABLE;
import static org.cilantro.manager.ParallelSession.getSession;
import static org.cilantro.utils.Validator.validateDelay;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import org.cilantro.actions.interfaces.elements.IClickableActions;
import org.cilantro.actions.interfaces.listeners.elements.IClickableActionsListener;
import org.cilantro.builders.Locator;
import org.cilantro.enums.PlatformType;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Handles all mouse related actions
 *
 * @author Rahul Saini
 * @since 16-Dec-2024
 */
public class ClickableActions extends FingersActions implements IClickableActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Handles all mouse related actions.
     *
     * @param locator Locator of the elements.
     *
     * @return {@link IClickableActions} instance object
     */
    public static IClickableActions withMouse (final Locator locator) {
        return new ClickableActions (locator);
    }

    private final IClickableActionsListener listener;

    ClickableActions (final Locator locator) {
        super (locator);
        this.listener = getSession ().getListener (CLICKABLE_ACTION);
    }

    @Override
    public void click () {
        LOGGER.traceEntry ();
        LOGGER.info ("Clicking on element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onClick (this.locator));
        final var session = getSession ();
        if (session.getPlatformType () == PlatformType.WEB || (session.getMobileSetting ()
            .getDevice ()
            .getApplication ()
            .getType () == WEB && session.getPlatformType () == IOS)) {
            pause (this.delaySetting.getBeforeClick ());
            scrollIntoView ();
            performElementAction (WebElement::click, this.locator);
        } else {
            pause (this.delaySetting.getBeforeClick ());
            performElementAction (WebElement::click, this.locator);
        }
        LOGGER.traceExit ();
    }

    @Override
    public void clickAndHold () {
        LOGGER.traceEntry ();
        LOGGER.info ("Click and hold on element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onClickAndHold (this.locator));
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.pause (validateDelay (this.delaySetting.getBeforeClick ()))
                .clickAndHold (element)
                .perform ();
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void doubleClick () {
        LOGGER.traceEntry ();
        LOGGER.info ("Double Click on element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onDoubleClick (this.locator));
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.pause (validateDelay (this.delaySetting.getBeforeClick ()))
                .doubleClick (element)
                .perform ();
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void dragTo (final Locator destination) {
        LOGGER.traceEntry ();
        LOGGER.info ("Drag and Drop on element: {} , {}", this.locator.getName (), destination.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onDragTo (this.locator, destination));
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.pause (validateDelay (this.delaySetting.getBeforeMouseMove ()))
                .dragAndDrop (element, find (destination, CLICKABLE))
                .perform ();
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void hover () {
        LOGGER.traceEntry ();
        LOGGER.info ("Hover on element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onHover (this.locator));
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.pause (validateDelay (this.delaySetting.getBeforeMouseMove ()))
                .moveToElement (element)
                .perform ();
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void rightClick () {
        LOGGER.traceEntry ();
        LOGGER.info ("Right Click on element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onRightClick (this.locator));
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.pause (validateDelay (this.delaySetting.getBeforeClick ()))
                .contextClick (element)
                .perform ();
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void submit () {
        LOGGER.traceEntry ();
        LOGGER.info ("Submitting element located by: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onSubmit (this.locator));
        performElementAction (WebElement::submit, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void clickByOffset (final int xOffset, final int yOffset) {
        LOGGER.traceEntry ();
        LOGGER.info ("Clicking on element using offset: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onClick (this.locator));
        final var session = getSession ();
        if (session.getPlatformType () == PlatformType.WEB) {
            scrollIntoView ();
            performElementAction ((driver, element) -> {
                final var actions = new Actions (driver);
                actions.moveToElement (element)
                        .perform ();
                actions.moveByOffset (xOffset, yOffset)
                        .perform ();
                actions.click ()
                        .perform ();
            }, this.locator);
        }
        LOGGER.traceExit ();
    }

    @Override
    public void alternateClick () {
        LOGGER.traceEntry ();
        LOGGER.info ("Alternate click on element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.alternateClick (this.locator));
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.moveToElement (element)
                .perform ();
            actions.click (element)
                .perform ();
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void jsxClick() {
        LOGGER.info ("Click element located by [{}] using javascript executer.", this.locator.getName());
        ofNullable(this.listener).ifPresent(l -> l.onJsxClick(this.locator));
        performElementAction( e-> withDriver().executeScript("arguments[0].click();", e), this.locator);
    }
}
