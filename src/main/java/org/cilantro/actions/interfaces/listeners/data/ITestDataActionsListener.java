

package org.cilantro.actions.interfaces.listeners.data;

import org.cilantro.actions.interfaces.listeners.XchangeListener;

/**
 * Test Data action listener.
 *
 * @author Rahul Saini
 * @since 28-Dec-2024
 */
public interface ITestDataActionsListener extends XchangeListener {
    /**
     * Handle `get` method from TestDataAction
     *
     * @param dataClass Test data class
     * @param <T> Type of test data class
     */
    default <T> void onGet (final Class<T> dataClass) {
        // not implemented
    }

    /**
     * Handle row method from TestDataAction
     *
     * @param index Row index
     */
    default void onRow (final int index) {
        // not implemented
    }

    /**
     * Handle row method from TestDataAction
     */
    default void onRows () {
        // not implemented
    }
}
