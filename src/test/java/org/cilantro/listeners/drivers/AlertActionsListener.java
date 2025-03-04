package org.cilantro.listeners.drivers;

import static io.qameta.allure.Allure.step;
import static java.text.MessageFormat.format;

import org.cilantro.actions.interfaces.listeners.drivers.IAlertActionsListener;

public class AlertActionsListener implements IAlertActionsListener {
    @Override
    public void onAccept () {
        step ("Accepting Alert");
    }

    @Override
    public void onAccept (final String text) {
        step (format ("Accepting Prompt Alert after entering [{0}] text", text));
    }

    @Override
    public void onDismiss () {
        step ("Dismissing Alert");
    }

    @Override
    public void onVerifyAccept (final String text) {
        step (format ("Verifying Alert message after Accepting Prompt Alert by entering [{0}] text", text));
    }

    @Override
    public void onVerifyAccept () {
        step ("Verifying Alert message after Accepting it");
    }

    @Override
    public void onVerifyDismiss () {
        step ("Verifying Alert message after Dismissing it");
    }
}
