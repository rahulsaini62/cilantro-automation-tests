

package org.cilantro.config.ui;

import lombok.Data;

/**
 * Screenshot setting.
 *
 * @author Rahul Saini
 * @since 13-Dec-2024
 */
@Data
public class ScreenshotSetting {
    private boolean enabled   = false;
    private String  extension = "png";
    private String  path      = "./screenshots";
    private String  prefix    = "SCR";
}
