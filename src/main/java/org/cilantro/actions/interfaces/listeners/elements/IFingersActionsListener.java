

package org.cilantro.actions.interfaces.listeners.elements;

import org.openqa.selenium.WebElement;
import org.cilantro.actions.interfaces.listeners.XchangeListener;
import org.cilantro.builders.Locator;

/**
 * Handles all multi-fingers actions events.
 *
 * @author Rahul Saini
 * @since 09-Dec-2024
 */
public interface IFingersActionsListener extends XchangeListener {
    /**
     * Handle zoom in method.
     *
     * @param locator Locator of the element.
     */
    default void onZoomIn (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle zoom in method.
     *
     * @param element Locator of the element.
     */
    default void onZoomIn (final WebElement element) {
        // not implemented.
    }

    /**
     * Handle zoom out method.
     *
     * @param locator Locator of the element.
     */
    default void onZoomOut (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle zoom out method.
     *
     * @param element Locator of the element.
     */
    default void onZoomOut (final WebElement element) {
        // not implemented.
    }
}
