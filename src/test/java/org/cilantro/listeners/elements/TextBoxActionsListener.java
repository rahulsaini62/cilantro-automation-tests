package org.cilantro.listeners.elements;

import static io.qameta.allure.Allure.step;
import static java.text.MessageFormat.format;

import org.cilantro.actions.interfaces.listeners.elements.ITextBoxActionsListener;
import org.cilantro.builders.Locator;

public class TextBoxActionsListener implements ITextBoxActionsListener {
    @Override
    public void onEnterText (final Locator locator, final String text) {
        step (format ("Entering text [{0}] in element [{1}]...", text, locator.getName ()));
    }
}
