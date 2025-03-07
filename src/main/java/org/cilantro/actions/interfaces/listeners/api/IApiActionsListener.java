

package org.cilantro.actions.interfaces.listeners.api;

import org.cilantro.actions.interfaces.listeners.XchangeListener;
import org.cilantro.builders.ApiResponse;

/**
 * API Actions listener.
 *
 * @author Rahul Saini
 * @since 06-Dec-2024
 */
public interface IApiActionsListener extends XchangeListener {
    /**
     * Handle API `execute` method.
     *
     * @param response {@link ApiResponse} instance
     */
    default void onExecute (final ApiResponse response) {
        // not implemented
    }
}
