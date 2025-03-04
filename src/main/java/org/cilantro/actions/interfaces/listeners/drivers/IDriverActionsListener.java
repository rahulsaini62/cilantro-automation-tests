

package org.cilantro.actions.interfaces.listeners.drivers;

import java.time.Duration;

import org.cilantro.actions.interfaces.listeners.XchangeListener;

/**
 * Handle all driver actions event.
 *
 * @author Rahul Saini
 * @since 09-Dec-2024
 */
public interface IDriverActionsListener extends XchangeListener {
    /**
     * Handle execute script method.
     *
     * @param script Script to be executed
     * @param args Arguments to be used in the script
     */
    default void onExecuteScript (final String script, final Object... args) {
        // not implemented.
    }

    /**
     * Handle pause method.
     *
     * @param time Time till executions to be paused
     */
    default void onPause (final Duration time) {
        // not implemented.
    }

    /**
     * Handle save logs method.
     */
    default void onSaveLogs () {
        // not implemented.
    }

    /**
     * Handle wait until method.
     */
    default void onWaitUntil () {
        // not implemented.
    }
}
