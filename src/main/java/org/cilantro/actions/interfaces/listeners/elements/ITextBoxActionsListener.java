

package org.cilantro.actions.interfaces.listeners.elements;

import org.openqa.selenium.WebElement;
import org.cilantro.actions.interfaces.listeners.XchangeListener;
import org.cilantro.builders.Locator;

/**
 * Handle all text box action events.
 *
 * @author Rahul Saini
 * @since 09-Dec-2024
 */
public interface ITextBoxActionsListener extends XchangeListener {
    /**
     * Handle enter text method.
     *
     * @param locator Locator of the element.
     * @param text Text to enter into text box
     */
    default void onEnterText (final Locator locator, final String text) {
        // not implemented.
    }

    /**
     * Handle enter text method.
     *
     * @param element Locator of the element.
     * @param text Text to enter into text box
     */
    default void onEnterText (final WebElement element, final String text) {
        // not implemented.
    }
}
