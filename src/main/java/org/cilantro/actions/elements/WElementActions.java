package org.cilantro.actions.elements;

import static com.google.common.truth.Truth.assertWithMessage;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.cilantro.actions.CommonActions.*;
import static org.cilantro.actions.drivers.DriverActions.withDriver;
import static org.cilantro.enums.ListenerType.ELEMENT_ACTION;
import static org.cilantro.manager.ParallelSession.getSession;

import com.google.common.truth.BooleanSubject;
import com.google.common.truth.StringSubject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.cilantro.actions.interfaces.elements.IElementActions;
import org.cilantro.actions.interfaces.listeners.elements.IElementActionsListener;
import org.cilantro.config.ui.DelaySetting;

public class WElementActions implements IElementActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Handle all other element actions
     *
     * @param element Locator of the element
     *
     * @return {@link IElementActions} instance objects
     */
    public static IElementActions onElement (final WebElement element) {
        return new WElementActions (element);
    }

    protected final DelaySetting            delaySetting;
    protected final WebElement              element;
    private final   IElementActionsListener listener;

    WElementActions (final WebElement element) {
        this.element = element;
        this.listener = getSession ().getListener (ELEMENT_ACTION);
        this.delaySetting = getSession ().getSetting ()
            .getUi ()
            .getDelay ();
    }

    /**
     * Clear text, selection of element.
     */
    @Override
    public void clear () {
        LOGGER.traceEntry ();
        LOGGER.info ("Clearing element located by: {}", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onClear (this.element));
        pause (this.delaySetting.getBeforeTyping ());
        performElementAction (WebElement::clear, this.element);
        LOGGER.traceExit ();
    }

    /**
     * Gets the value of the attribute of the element.
     *
     * @param attribute attribute of the element
     *
     * @return value of the attribute of the element
     */
    @Override
    public String getAttribute (final String attribute) {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting attribute: {} of element located by: {}", attribute, this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onGetAttribute (this.element, attribute));
        LOGGER.traceExit ();
        return getElementAttribute (e -> e.getAttribute (attribute), this.element, EMPTY);
    }

    /**
     * Gets the styling attribute of the element.
     *
     * @param styleName attribute of the element
     *
     * @return value of the styling attribute of the element
     */
    @Override
    public String getStyle (final String styleName) {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting style: {} of element located by: {}", styleName, this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onGetStyle (this.element, styleName));
        LOGGER.traceExit ();
        return getElementAttribute (e -> e.getCssValue (styleName), this.element, EMPTY);
    }

    /**
     * Gets the text of the element.
     *
     * @return text of the element
     */
    @Override
    public String getText () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting text of element located by: {}", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onGetText (this.element));
        return LOGGER.traceExit (getElementAttribute (WebElement::getText, this.element, EMPTY));
    }

    /**
     * Gets the value if the element is displayed.
     *
     * @return true if the element is displayed, false otherwise
     */
    @Override
    public boolean isDisplayed () {
        LOGGER.traceEntry ();
        LOGGER.info ("Checking if element located by: {} is displayed", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onIsDisplayed (this.element));
        return LOGGER.traceExit (getElementAttribute (WebElement::isDisplayed, this.element, false));
    }


    public boolean isDisplayedWithoutWait () {
        LOGGER.traceEntry ();
        LOGGER.info ("Checking if element located by: {} is displayed without wait", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onIsDisplayed (this.element));
        return LOGGER.traceExit (getElementAttribute (WebElement::isDisplayed, this.element, false));
    }
    /**
     * Gets the value if the element is enabled.
     *
     * @return true if the element is enabled, false otherwise
     */
    @Override
    public boolean isEnabled () {
        LOGGER.traceEntry ();
        LOGGER.info ("Checking if element located by: {} is enabled", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onIsEnabled (this.element));
        return LOGGER.traceExit (getElementAttribute (WebElement::isEnabled, this.element, false));
    }

    /**
     * Gets the value if the element is selected.
     *
     * @return true if the element is selected, false otherwise
     */
    @Override
    public boolean isSelected () {
        LOGGER.traceEntry ();
        LOGGER.info ("Checking if element located by: {} is selected", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onIsSelected (this.element));
        return LOGGER.traceExit (getElementAttribute (WebElement::isSelected, this.element, false));
    }

    /**
     * Scroll the element into view.
     */
    @Override
    public void scrollIntoView () {
        LOGGER.info ("Scrolling element located by [{}] into view", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onScrollIntoView (this.element));
        pause (this.delaySetting.getBeforeMouseMove ());
        performElementAction (e -> withDriver ().executeScript ("arguments[0].scrollIntoView(true);", e), this.element);
    }

    /**
     * Verify attribute of element.
     *
     * @param attribute attribute to verify
     *
     * @return {@link StringSubject} to verify the result
     */
    @Override
    public StringSubject verifyAttribute (final String attribute) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying attribute of {}", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onVerifyAttribute (this.element, attribute));
        LOGGER.traceExit ();
        return assertWithMessage (attribute).that (getAttribute (attribute));
    }

    /**
     * Verify if element is displayed.
     *
     * @return {@link BooleanSubject} to verify the result
     */
    @Override
    public BooleanSubject verifyIsDisplayed () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying element {} is displayed", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onVerifyIsDisplayed (this.element));
        LOGGER.traceExit ();
        return assertWithMessage ("Displayed").that (isDisplayed ());
    }

    /**
     * Verify if element is enabled.
     *
     * @return {@link BooleanSubject} to verify the result
     */
    @Override
    public BooleanSubject verifyIsEnabled () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying element {} is enabled", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onVerifyIsEnabled (this.element));
        LOGGER.traceExit ();
        return assertWithMessage ("Enabled").that (isEnabled ());
    }

    /**
     * Verify if element is selected.
     *
     * @return {@link BooleanSubject} to verify the result
     */
    @Override
    public BooleanSubject verifyIsSelected () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying element {} is selected", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onVerifyIsSelected (this.element));
        LOGGER.traceExit ();
        return assertWithMessage ("Selected").that (isSelected ());
    }

    /**
     * Verify style of element.
     *
     * @param styleName attribute to verify
     *
     * @return {@link StringSubject} to verify the result
     */
    @Override
    public StringSubject verifyStyle (final String styleName) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying style of {}", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onVerifyStyle (this.element, styleName));
        LOGGER.traceExit ();
        return assertWithMessage (styleName).that (getStyle (styleName));
    }

    /**
     * Verify text of element.
     *
     * @return {@link StringSubject} to verify the result
     */
    @Override
    public StringSubject verifyText () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying text of {}", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onVerifyText (this.element));
        LOGGER.traceExit ();
        return assertWithMessage ("Text").that (getText ().trim ());
    }
}
