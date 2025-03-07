

package org.cilantro.actions.interfaces.device;

/**
 * Handle all device specific actions.
 *
 * @author Rahul Saini
 * @since 31-Dec-2024
 */
public interface IDeviceActions {
    /**
     * Hides the keyboard if visible.
     */
    void hideKeyboard ();

    /**
     * Gets the keyboard state whether it is visible or not.
     *
     * @return true, if visible.
     */
    boolean isKeyboardVisible ();

    /**
     * Starts video recording.
     */
    void startRecording ();

    /**
     * Stops video recording.
     */
    void stopRecording ();
}
