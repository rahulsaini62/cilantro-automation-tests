

package org.cilantro.actions.interfaces.listeners.elements;

import org.openqa.selenium.WebElement;
import org.cilantro.actions.interfaces.listeners.XchangeListener;
import org.cilantro.builders.Locator;

/**
 * Handle all clickable actions events.
 *
 * @author Rahul Saini
 * @since 09-Dec-2024
 */
public interface IClickableActionsListener extends XchangeListener {
    /**
     * Handle click method
     *
     * @param locator Locator of the element.
     */
    default void onClick (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle click method
     *
     * @param element Locator of the element.
     */
    default void onClick (final WebElement element) {
        // not implemented.
    }

    /**
     * Handle click and hold method.
     *
     * @param locator Locator of the element.
     */
    default void onClickAndHold (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle click and hold method.
     *
     * @param element Locator of the element.
     */
    default void onClickAndHold (final WebElement element) {
        // not implemented.
    }

    /**
     * Handle double click method.
     *
     * @param locator Locator of the element.
     */
    default void onDoubleClick (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle double click method.
     *
     * @param element Locator of the element.
     */
    default void onDoubleClick (final WebElement element) {
        // not implemented.
    }

    /**
     * Handle drag to the target element.
     *
     * @param source source element.
     * @param destination target element
     */
    default void onDragTo (final Locator source, final Locator destination) {
        // not implemented.
    }

    /**
     * Handle drag to the target element.
     *
     * @param source source element.
     * @param destination target element
     */
    default void onDragTo (final WebElement source, final WebElement destination) {
        // not implemented.
    }

    /**
     * Handle hover method.
     *
     * @param locator Locator of the element.
     */
    default void onHover (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle hover method.
     *
     * @param element Locator of the element.
     */
    default void onHover (final WebElement element) {
        // not implemented.
    }

    /**
     * Handle right click method.
     *
     * @param locator Locator of the element.
     */
    default void onRightClick (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle right click method.
     *
     * @param element Locator of the element.
     */
    default void onRightClick (final WebElement element) {
        // not implemented.
    }

    /**
     * Handle submit method.
     *
     * @param locator Locator of the element.
     */
    default void onSubmit (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle submit method.
     *
     * @param element Locator of the element.
     */
    default void onSubmit (final WebElement element) {
        // not implemented.
    }

    void alternateClick (Locator locator);

    void alternateClick (WebElement element);

    void onJsxClick(Locator locator);
}
