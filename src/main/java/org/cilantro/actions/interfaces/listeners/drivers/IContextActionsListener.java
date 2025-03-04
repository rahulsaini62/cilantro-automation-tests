

package org.cilantro.actions.interfaces.listeners.drivers;

import org.cilantro.actions.interfaces.listeners.XchangeListener;

/**
 * Handle Context actions related events.
 *
 * @author Rahul Saini
 * @since 09-Dec-2024
 */
public interface IContextActionsListener extends XchangeListener {
    /**
     * Handle contexts method.
     */
    default void onContexts () {
        // not implemented.
    }

    /**
     * Handle current context method.
     */
    default void onCurrentContext () {
        // not implemented.
    }

    /**
     * Handle switch to native view method.
     */
    default void onSwitchToNative () {
        // not implemented.
    }

    /**
     * Handle switch to web view method.
     *
     * @param name Name of WebView
     */
    default void onSwitchToWebView (final String name) {
        // not implemented.
    }

    /**
     * Handle switch to web view method.
     */
    default void onSwitchToWebView () {
        // not implemented.
    }
}
