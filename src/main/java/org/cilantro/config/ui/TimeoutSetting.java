

package org.cilantro.config.ui;

import lombok.Data;

/**
 * Playback setting.
 *
 * @author Rahul Saini
 * @since 17-Dec-2024
 */
@Data
public class TimeoutSetting {
    private int explicitWait    = 10;
    private int highlightDelay  = 100;
    private int implicitWait    = 1;
    private int pageLoadTimeout = 30;
    private int scriptTimeout   = 10;
}
