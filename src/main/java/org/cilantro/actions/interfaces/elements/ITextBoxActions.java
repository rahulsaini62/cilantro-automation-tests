

package org.cilantro.actions.interfaces.elements;

/**
 * All text box related actions
 *
 * @author Rahul Saini
 * @since 15-Dec-2024
 */
public interface ITextBoxActions extends IClickableActions {
    /**
     * Enter text in text field.
     *
     * @param text text to enter
     */
    void enterText (final String text);

    void sendKeys (String countryName);
}
