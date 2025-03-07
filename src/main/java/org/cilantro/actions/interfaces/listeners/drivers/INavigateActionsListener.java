

package org.cilantro.actions.interfaces.listeners.drivers;

import org.cilantro.actions.interfaces.listeners.XchangeListener;

/**
 * Handle navigate actions events.
 *
 * @author Rahul Saini
 * @since 09-Dec-2024
 */
public interface INavigateActionsListener extends XchangeListener {
    /**
     * Handle back method.
     */
    default void onBack () {
        // not implemented.
    }

    /**
     * Handle forward method.
     */
    default void onForward () {
        // not implemented.
    }

    /**
     * Handle get url method.
     */
    default void onGetUrl () {
        // not implemented.
    }

    /**
     * Handle refresh method.
     */
    default void onRefresh () {
        // not implemented.
    }

    /**
     * handle `to` method.
     *
     * @param url URL to navigate to
     */
    default void onTo (final String url) {
        // not implemented.
    }

    /**
     * Handle event of `toBaseUrl` method.
     */
    default void onToBaseUrl () {
        // not implemented.
    }

    /**
     * Handle verify URL method.
     */
    default void onVerifyUrl () {
        // not implemented.
    }
}
