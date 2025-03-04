package org.cilantro.listeners.elements;

import static io.qameta.allure.Allure.step;
import static java.text.MessageFormat.format;

import org.cilantro.actions.interfaces.listeners.elements.IDropDownActionsListener;
import org.cilantro.builders.Locator;

public class DropDownActionsListener implements IDropDownActionsListener {
    @Override
    public void onDeselectAll (final Locator locator) {
        step (format ("Deselecting all items on element [{0}]...", locator.getName ()));
    }

    @Override
    public void onDeselectByIndex (final Locator locator, final int index) {
        step (format ("Deselecting item on element [{0}] by index [{1}]...", locator.getName (), index));
    }

    @Override
    public void onDeselectByText (final Locator locator, final String text) {
        step (format ("Deselecting item on element [{0}] by text [{1}]...", locator.getName (), text));
    }

    @Override
    public void onDeselectByValue (final Locator locator, final String value) {
        step (format ("Deselecting item on element [{0}] by value [{1}]...", locator.getName (), value));
    }

    @Override
    public void onSelectByIndex (final Locator locator, final int index) {
        step (format ("Selecting item on element [{0}] by index [{1}]...", locator.getName (), index));
    }

    @Override
    public void onSelectByText (final Locator locator, final String text) {
        step (format ("Selecting item on element [{0}] by text [{1}]...", locator.getName (), text));
    }

    @Override
    public void onSelectByValue (final Locator locator, final String value) {
        step (format ("Selecting item on element [{0}] by value [{1}]...", locator.getName (), value));
    }

    @Override
    public void onSelectedItem (final Locator locator) {
        step (format ("Getting Selected item on element [{0}]...", locator.getName ()));
    }

    @Override
    public void onSelectedItems (final Locator locator) {
        step (format ("Getting Selected items on element [{0}]...", locator.getName ()));
    }

    @Override
    public void onVerifySelectedItem (final Locator locator) {
        step (format ("Verifying the selected item on element [{0}]...", locator.getName ()));
    }

    @Override
    public void onVerifySelectedItems (final Locator locator) {
        step (format ("Verifying the selected items on element [{0}]...", locator.getName ()));
    }
}
