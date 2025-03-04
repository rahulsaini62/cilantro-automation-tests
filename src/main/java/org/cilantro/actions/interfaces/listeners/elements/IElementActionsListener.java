

package org.cilantro.actions.interfaces.listeners.elements;

import org.openqa.selenium.WebElement;
import org.cilantro.actions.interfaces.listeners.XchangeListener;
import org.cilantro.builders.Locator;

/**
 * Handle all element actions events.
 *
 * @author Rahul Saini
 * @since 09-Dec-2024
 */
public interface IElementActionsListener extends XchangeListener {
    /**
     * Handle clear method.
     *
     * @param locator Locator of the element.
     */
    default void onClear (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle clear method.
     *
     * @param element WebElement.
     */
    default void onClear (final WebElement element) {
        // not implemented.
    }

    /**
     * Handle get attribute method.
     *
     * @param locator Locator of the element.
     * @param attribute Attribute name
     */
    default void onGetAttribute (final Locator locator, final String attribute) {
        // not implemented.
    }

    /**
     * Handle get attribute method.
     *
     * @param element Locator of the element.
     * @param attribute Attribute name
     */
    default void onGetAttribute (final WebElement element, final String attribute) {
        // not implemented.
    }

    /**
     * Handle get style method.
     *
     * @param locator Locator of the element.
     * @param styleName Style name
     */
    default void onGetStyle (final Locator locator, final String styleName) {
        // not implemented.
    }

    /**
     * Handle get style method.
     *
     * @param element Locator of the element.
     * @param styleName Style name
     */
    default void onGetStyle (final WebElement element, final String styleName) {
        // not implemented.
    }

    /**
     * Handle get text method.
     *
     * @param locator Locator of the element.
     */
    default void onGetText (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle get text method.
     *
     * @param element Locator of the element.
     */
    default void onGetText (final WebElement element) {
        // not implemented.
    }

    /**
     * Handle is displayed method.
     *
     * @param locator Locator of the element.
     */
    default void onIsDisplayed (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle is displayed method.
     *
     * @param element Locator of the element.
     */
    default void onIsDisplayed (final WebElement element) {
        // not implemented.
    }

    /**
     * Handle is enabled method.
     *
     * @param locator Locator of the element.
     */
    default void onIsEnabled (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle is enabled method.
     *
     * @param element WebElement
     */
    default void onIsEnabled (final WebElement element) {
        // not implemented.
    }

    /**
     * Handle is selected method.
     *
     * @param locator Locator of the element.
     */
    default void onIsSelected (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle is selected method.
     *
     * @param element Locator of the element.
     */
    default void onIsSelected (final WebElement element) {
        // not implemented.
    }

    /**
     * Handle scroll into view method.
     *
     * @param locator Locator of the element.
     */
    default void onScrollIntoView (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle scroll into view method.
     *
     * @param element WebElement
     */
    default void onScrollIntoView (final WebElement element) {
        // not implemented.
    }

    /**
     * Handle verify attribute method.
     *
     * @param locator Locator of the element.
     * @param attribute attribute name
     */
    default void onVerifyAttribute (final Locator locator, final String attribute) {
        // not implemented.
    }

    /**
     * Handle verify attribute method.
     *
     * @param element Locator of the element.
     * @param attribute attribute name
     */
    default void onVerifyAttribute (final WebElement element, final String attribute) {
        // not implemented.
    }

    /**
     * Handle verify is displayed method.
     *
     * @param locator Locator of the element.
     */
    default void onVerifyIsDisplayed (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle verify is displayed method.
     *
     * @param element WebElement.
     */
    default void onVerifyIsDisplayed (final WebElement element) {
        // not implemented.
    }

    /**
     * Handle verify is enabled method.
     *
     * @param locator Locator of the element.
     */
    default void onVerifyIsEnabled (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle verify is enabled method.
     *
     * @param element WebElement
     */
    default void onVerifyIsEnabled (final WebElement element) {
        // not implemented.
    }

    /**
     * Handle verify is selected method.
     *
     * @param locator Locator of the element.
     */
    default void onVerifyIsSelected (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle verify is selected method.
     *
     * @param element WebElement.
     */
    default void onVerifyIsSelected (final WebElement element) {
        // not implemented.
    }

    /**
     * Handle verify style method.
     *
     * @param locator Locator of the element.
     * @param styleName Style name
     */
    default void onVerifyStyle (final Locator locator, final String styleName) {
        // not implemented.
    }

    /**
     * Handle verify style method.
     *
     * @param element Locator of the element.
     * @param styleName Style name
     */
    default void onVerifyStyle (final WebElement element, final String styleName) {
        // not implemented.
    }

    /**
     * Handle verify text method.
     *
     * @param locator Locator of the element.
     */
    default void onVerifyText (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle verify text method.
     *
     * @param element WebElement.
     */
    default void onVerifyText (final WebElement element) {
        // not implemented.
    }
}
