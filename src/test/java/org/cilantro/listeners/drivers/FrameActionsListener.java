package org.cilantro.listeners.drivers;

import static io.qameta.allure.Allure.step;
import static java.text.MessageFormat.format;

import org.cilantro.actions.interfaces.listeners.drivers.IFrameActionsListener;

public class FrameActionsListener implements IFrameActionsListener {
    @Override
    public void onSwitchTo (final String frameNameOrId) {
        step (format ("Switching to Frame ID [{0}]...", frameNameOrId));
    }

    @Override
    public void onSwitchTo (final int index) {
        step (format ("Switching to Frame on index [{0}]...", index));
    }

    @Override
    public void onSwitchToParent () {
        step ("Switching to Parent Frame...");
    }
}
