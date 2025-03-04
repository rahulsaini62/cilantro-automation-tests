

package org.cilantro.actions.interfaces.listeners.device;

import org.cilantro.actions.interfaces.listeners.XchangeListener;

/**
 * Handles all the common device specific events.
 *
 * @author Rahul Saini
 * @since 03-Jun-2024
 */
public interface IDeviceActionsListener extends XchangeListener {
    /**
     * Handle hide keyboard event.
     */
    default void onHideKeyboard () {
        // not implemented.
    }

    /**
     * Handle is keyboard visible event.
     */
    default void onIsKeyboardVisible () {
        // not implemented.
    }

    /**
     * Handle start recording event.
     */
    default void onStartRecording () {
        // not implemented.
    }

    /**
     * Handle stop recording event.
     */
    default void onStopRecording () {
        // not implemented.
    }
}
