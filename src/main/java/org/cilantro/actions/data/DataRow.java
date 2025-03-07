package org.cilantro.actions.data;

import static org.cilantro.utils.Validator.checkIndex;
import static java.util.Arrays.stream;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.List;
import java.util.Objects;

import org.cilantro.actions.interfaces.data.IDataRow;
import org.apache.logging.log4j.Logger;

/**
 * Gets test data in data row.
 *
 * @author Rahul Saini
 * @since 28-Dec-2024
 */
class DataRow implements IDataRow {
    private static final Logger LOGGER = getLogger ();

    private final Object[] headers;
    private final Object[] rowData;

    DataRow (final Object[] headers, final Object[] rowData) {
        this.headers = headers;
        this.rowData = rowData;
    }

    @SuppressWarnings ("unchecked")
    @Override
    public <T> T cell (final int index) {
        checkIndex (index, this.rowData.length);
        LOGGER.info ("Getting Cell data at [{}] index", index);
        return (T) this.rowData[index];
    }

    @Override
    public <T> T cell (final String name) {
        var colIndex = 0;
        for (var index = 0; index < this.headers.length; index++) {
            if (this.headers[index].equals (name)) {
                colIndex = index;
                break;
            }
        }
        LOGGER.info ("Getting Cell data in [{}] column", name);
        return cell (colIndex);
    }

    @SuppressWarnings ("unchecked")
    @Override
    public <T> List<T> cells () {
        LOGGER.info ("Getting all the Cell data");
        return stream ((T[]) this.rowData).toList ();
    }

    @Override
    public boolean isEmpty () {
        return stream (this.rowData).allMatch (Objects::isNull);
    }
}
