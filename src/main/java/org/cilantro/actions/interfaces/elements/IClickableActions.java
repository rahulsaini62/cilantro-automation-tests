

package org.cilantro.actions.interfaces.elements;

/**
 * All Clickable actions
 *
 * @author Rahul Saini
 * @since 15-Dec-2024
 */
public interface IClickableActions extends IFingersActions {
    /**
     * Click on element
     */
    void click ();

    /**
     * LongPress on element
     */
    void clickAndHold ();

    /**
     * DoubleClick on element
     */
    void doubleClick ();

    /**
     * Hover on element
     */
    void hover ();

    /**
     * RightClick on element
     */
    void rightClick ();

    /**
     * Submit the element.
     */
    void submit ();
    void clickByOffset (int xOffset, int yOffset);
    void alternateClick ();

    void jsxClick();
}
