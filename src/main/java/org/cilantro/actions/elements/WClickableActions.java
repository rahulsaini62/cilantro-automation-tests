package org.cilantro.actions.elements;

import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.cilantro.actions.CommonActions.pause;
import static org.cilantro.actions.CommonActions.performElementAction;
import static org.cilantro.actions.elements.ElementFinder.find;
import static org.cilantro.enums.ApplicationType.WEB;
import static org.cilantro.enums.ListenerType.CLICKABLE_ACTION;
import static org.cilantro.enums.PlatformType.IOS;
import static org.cilantro.manager.ParallelSession.getSession;
import static org.cilantro.utils.Validator.validateDelay;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.cilantro.actions.interfaces.elements.IClickableActions;
import org.cilantro.actions.interfaces.listeners.elements.IClickableActionsListener;
import org.cilantro.enums.PlatformType;

public class WClickableActions extends WFingersActions implements IClickableActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Handles all mouse related actions.
     *
     * @param element Locator of the elements.
     *
     * @return {@link IClickableActions} instance object
     */
    public static IClickableActions withMouse (final WebElement element) {
        return new WClickableActions (element);
    }

    private final IClickableActionsListener listener;

    WClickableActions (final WebElement element) {
        super (element);
        this.listener = getSession ().getListener (CLICKABLE_ACTION);
    }

    /**
     * Click on element
     */
    @Override
    public void click () {
        LOGGER.traceEntry ();
        LOGGER.info ("Clicking on element: {}", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onClick (this.element));
        final var session = getSession ();
        if (session.getPlatformType () == PlatformType.WEB || (session.getMobileSetting ()
            .getDevice ()
            .getApplication ()
            .getType () == WEB && session.getPlatformType () == IOS)) {
            pause (this.delaySetting.getBeforeClick ());
            scrollIntoView ();
            performElementAction (WebElement::click, this.element);
        } else {
            pause (this.delaySetting.getBeforeClick ());
            performElementAction (WebElement::click, this.element);
        }
        LOGGER.traceExit ();
    }

    /**
     * LongPress on element
     */
    @Override
    public void clickAndHold () {
        LOGGER.traceEntry ();
        LOGGER.info ("Click and hold on element: {}", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onClickAndHold (this.element));
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.pause (validateDelay (this.delaySetting.getBeforeClick ()))
                .clickAndHold (element)
                .perform ();
        }, this.element);
        LOGGER.traceExit ();
    }

    /**
     * DoubleClick on element
     */
    @Override
    public void doubleClick () {
        LOGGER.traceEntry ();
        LOGGER.info ("Double Click on element: {}", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onDoubleClick (this.element));
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.pause (validateDelay (this.delaySetting.getBeforeClick ()))
                .doubleClick (element)
                .perform ();
        }, this.element);
        LOGGER.traceExit ();
    }

    /**
     * Hover on element
     */
    @Override
    public void hover () {
        LOGGER.traceEntry ();
        LOGGER.info ("Hover on element: {}", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onHover (this.element));
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.pause (validateDelay (this.delaySetting.getBeforeMouseMove ()))
                .moveToElement (element)
                .perform ();
        }, this.element);
        LOGGER.traceExit ();
    }

    /**
     * RightClick on element
     */
    @Override
    public void rightClick () {
        LOGGER.traceEntry ();
        LOGGER.info ("Right Click on element: {}", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onRightClick (this.element));
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.pause (validateDelay (this.delaySetting.getBeforeClick ()))
                .contextClick (element)
                .perform ();
        }, this.element);
        LOGGER.traceExit ();
    }

    /**
     * Submit the element.
     */
    @Override
    public void submit () {
        LOGGER.traceEntry ();
        LOGGER.info ("Submitting element located by: {}", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onSubmit (this.element));
        performElementAction (WebElement::submit, this.element);
        LOGGER.traceExit ();
    }

    /**
     * @param xOffset
     * @param yOffset
     */
    @Override
    public void clickByOffset (final int xOffset, final int yOffset) {
        LOGGER.traceEntry ();
        LOGGER.info ("Clicking on element using offset: {}", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onClick (this.element));
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
            }, this.element);
        }
        LOGGER.traceExit ();

    }

    /**
     *
     */
    @Override
    public void alternateClick () {
        LOGGER.traceEntry ();
        LOGGER.info ("Alternate click on element: {}", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.alternateClick (this.element));
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.moveToElement (element)
                .perform ();
            actions.click (element)
                .perform ();
        }, this.element);
        LOGGER.traceExit ();
    }

    @Override
    public void jsxClick() {

    }
}
