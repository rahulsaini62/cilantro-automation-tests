package org.cilantro.config;

import org.cilantro.enums.TestDataSource;
import lombok.Data;

/**
 * Test Data Setting.
 *
 * @author Rahul Saini
 * @since 18-Dec-2024
 */
@Data
public class TestDataSetting {
    private String         extension = "xlsx";
    private boolean        external;
    private String         path      = "test-data";
    private TestDataSource type      = TestDataSource.EXCEL;
}
