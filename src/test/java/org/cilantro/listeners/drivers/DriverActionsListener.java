package org.cilantro.listeners.drivers;

import static io.qameta.allure.Allure.step;
import static java.text.MessageFormat.format;

import java.time.Duration;

import org.cilantro.actions.interfaces.listeners.drivers.IDriverActionsListener;

public class DriverActionsListener implements IDriverActionsListener {
    @Override
    public void onExecuteScript (final String script, final Object... args) {
        step (format ("Executing Script [{0}] with args [{1}]...", script, args));
    }

    @Override
    public void onPause (final Duration time) {
        step (format ("Pausing till [{0}]...", time));
    }

    @Override
    public void onSaveLogs () {
        step ("Saving all the available logs...");
    }

    @Override
    public void onWaitUntil () {
        step ("Waiting until a specific conditions...");
    }
}
