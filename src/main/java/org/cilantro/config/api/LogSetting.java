package org.cilantro.config.api;

import lombok.Data;

/**
 * Appium server log settings.
 *
 * @author Rahul Saini
 * @since 07-Dec-2024
 */
@Data
public class LogSetting {
    private boolean enable   = true;
    private boolean request  = true;
    private boolean response = true;
}
