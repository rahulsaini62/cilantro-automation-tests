package org.cilantro.listeners.drivers;

import static io.qameta.allure.Allure.step;
import static java.text.MessageFormat.format;

import org.cilantro.actions.interfaces.listeners.drivers.INavigateActionsListener;

public class NavigateActionsListener implements INavigateActionsListener {
    @Override
    public void onBack () {
        step ("Navigating Back...");
    }

    @Override
    public void onForward () {
        step ("Navigating Forward...");
    }

    @Override
    public void onGetUrl () {
        step ("Getting the current page URL...");
    }

    @Override
    public void onRefresh () {
        step ("Refreshing current page...");
    }

    @Override
    public void onTo (final String url) {
        step (format ("Navigating to [{0}]...", url));
    }

    @Override
    public void onVerifyUrl () {
        step ("Verifying the current URL...");
    }
}
