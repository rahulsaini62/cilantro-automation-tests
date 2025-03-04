

package org.cilantro.actions.interfaces.elements;

import org.cilantro.builders.Locator;
import org.cilantro.enums.SwipeDirection;

/**
 * All single finger actions
 *
 * @author Rahul Saini
 * @since 15-Dec-2024
 */
public interface IFingerActions extends IElementActions {
    /**
     * Drag one element to another element.
     *
     * @param destination Target element
     */
    void dragTo (final Locator destination);

    /**
     * Swipe on the Mobile screen starting from center of the screen to the direction mentioned
     *
     * @param direction Direction to swipe in.
     */
    void swipe (SwipeDirection direction);

    /**
     * Swipe until element is displayed in the mentioned direction.
     *
     * @param direction Direction to swipe in
     */
    void swipeTill (SwipeDirection direction);

    /**
     * Taps on an element.
     */
    void tap ();
}
