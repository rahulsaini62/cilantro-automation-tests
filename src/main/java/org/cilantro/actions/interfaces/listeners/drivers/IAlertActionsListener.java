

package org.cilantro.actions.interfaces.listeners.drivers;

import org.cilantro.actions.interfaces.listeners.XchangeListener;

/**
 * Listener to handle Alert actions events.
 *
 * @author Rahul Saini
 * @since 09-Dec-2024
 */
public interface IAlertActionsListener extends XchangeListener {
    /**
     * Handle accept alert.
     */
    default void onAccept () {
        // not implemented.
    }

    /**
     * Handle Accept method for prompt alert.
     *
     * @param text Text to be entered in the Alert prompt
     */
    default void onAccept (final String text) {
        // not implemented.
    }

    /**
     * Handle dismiss alert.
     */
    default void onDismiss () {
        // not implemented.
    }

    /**
     * Handle verify accept alert method with prompt text.
     *
     * @param text Text to be entered in the Alert prompt
     */
    default void onVerifyAccept (final String text) {
        // not implemented.
    }

    /**
     * Handle verify accept alert method.
     */
    default void onVerifyAccept () {
        // not implemented.
    }

    /**
     * Handle dismiss alert method.
     */
    default void onVerifyDismiss () {
        // not implemented.
    }
}
