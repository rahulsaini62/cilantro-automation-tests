

package org.cilantro.actions.interfaces.data;

import java.util.List;

/**
 * Handle test data from different sources.
 *
 * @author Rahul Saini
 * @since 19-Dec-2024
 */
public interface ITestDataAction {
    /**
     * Gets the list test data objects.
     *
     * @param dataClass Data class object.
     * @param <T> Type of test data class.
     *
     * @return List of data objects
     */
    <T> List<T> get (Class<T> dataClass);

    /**
     * Process test data in a particular block or sheet in the test data file.
     *
     * @param blockName Block name of test data
     *
     * @return {@link ITestDataAction} instance
     */
    ITestDataAction inBlock (String blockName);

    /**
     * Gets data row.
     *
     * @param index Row index
     *
     * @return Test data row.
     */
    IDataRow row (int index);

    /**
     * Gets all the rows.
     *
     * @return List of rows.
     */
    List<IDataRow> rows ();
}
