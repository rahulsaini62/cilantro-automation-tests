package org.cilantro.listeners.drivers;

import static io.qameta.allure.Allure.step;
import static java.text.MessageFormat.format;

import org.cilantro.actions.interfaces.listeners.drivers.IContextActionsListener;

public class ContextActionsListener implements IContextActionsListener {
    @Override
    public void onContexts () {
        step ("Getting list of contexts");
    }

    @Override
    public void onCurrentContext () {
        step ("Getting current context");
    }

    @Override
    public void onSwitchToNative () {
        step ("Switching to Native context");
    }

    @Override
    public void onSwitchToWebView (final String name) {
        step (format ("Switching to Web view [{0}]", name));
    }

    @Override
    public void onSwitchToWebView () {
        step ("Switching to default Wev view context");
    }
}
