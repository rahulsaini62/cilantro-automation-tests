

package org.cilantro.actions.interfaces.listeners.drivers;

import org.cilantro.actions.interfaces.listeners.XchangeListener;

/**
 * Handle all cookies action events.
 *
 * @author Rahul Saini
 * @since 09-Dec-2024
 */
public interface ICookieActionsListener extends XchangeListener {
    /**
     * Handle cookie method.
     *
     * @param name Cookie name
     */
    default void onCookie (final String name) {
        // not implemented.
    }

    /**
     * Handle cookies method.
     */
    default void onCookies () {
        // not implemented.
    }

    /**
     * Handle delete cookie method.
     *
     * @param name Cookie name
     */
    default void onDelete (final String name) {
        // not implemented.
    }

    /**
     * Handle delete all method.
     */
    default void onDeleteAll () {
        // not implemented.
    }
}
