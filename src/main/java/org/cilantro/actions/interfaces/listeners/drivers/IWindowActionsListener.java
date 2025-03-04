

package org.cilantro.actions.interfaces.listeners.drivers;

import org.cilantro.actions.interfaces.listeners.XchangeListener;
import org.openqa.selenium.WindowType;

/**
 * Handle windows action events.
 *
 * @author Rahul Saini
 * @since 29-Dec-2024
 */
public interface IWindowActionsListener extends XchangeListener {
    default void onClose () {
        // not implemented.
    }

    default void onCurrentHandle () {
        // not implemented.
    }

    default void onFullScreen () {
        // not implemented.
    }

    default void onGetTitle () {
        // not implemented.
    }

    default void onHandles () {
        // not implemented.
    }

    default void onMaximize () {
        // not implemented.
    }

    default void onMinimize () {
        // not implemented.
    }

    default void onSwitchTo (final String nameOfHandle) {
        // not implemented.
    }

    default void onSwitchToDefault () {
        // not implemented.
    }

    default void onSwitchToNew (final WindowType windowType) {
        // not implemented.
    }

    default void onTakeScreenshot (final String fileName) {
        // not implemented.
    }

    default void onVerifyTitle () {
        // not implemented.
    }

    default void onViewportSize () {
        // not implemented.
    }
}
