

package org.cilantro.actions.interfaces.listeners.drivers;

import org.cilantro.actions.interfaces.listeners.XchangeListener;

/**
 * Handle frame actions listener.
 *
 * @author Rahul Saini
 * @since 09-Dec-2024
 */
public interface IFrameActionsListener extends XchangeListener {
    /**
     * Handle switch to method.
     *
     * @param frameNameOrId Name or ID of the IFrame
     */
    default void onSwitchTo (final String frameNameOrId) {
        // not implemented.
    }

    /**
     * Handle switch to method.
     *
     * @param index index of the IFrame
     */
    default void onSwitchTo (final int index) {
        // not implemented.
    }

    /**
     * Handle switch to parent method.
     */
    default void onSwitchToParent () {
        // not implemented.
    }
}
