

package org.cilantro.config.ui;

import java.util.List;

import lombok.Data;

/**
 * Appium server log settings.
 *
 * @author Rahul Saini
 * @since 07-Dec-2024
 */
@Data
public class LogSetting {
    private boolean      enable = true;
    private List<String> excludeLogs;
    private String       path   = "./logs";
}
