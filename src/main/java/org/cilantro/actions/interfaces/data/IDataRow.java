

package org.cilantro.actions.interfaces.data;

import java.util.List;

/**
 * Parses data row from data source.
 *
 * @author Rahul Saini
 * @since 19-Dec-2024
 */
public interface IDataRow {
    /**
     * Get cell data.
     *
     * @param index 1 based index of the cell.
     * @param <T> Type of cell data
     *
     * @return cell data.
     */
    <T> T cell (int index);

    /**
     * Get cell data.
     *
     * @param name Cell name.
     * @param <T> Type of cell data
     *
     * @return cell data.
     */
    <T> T cell (String name);

    /**
     * Get all the cells in the row.
     *
     * @param <T> Type of cell data
     *
     * @return List of cells.
     */
    <T> List<T> cells ();

    /**
     * Checks if the row is empty.
     *
     * @return true, if row has all cells empty.
     */
    boolean isEmpty ();
}
