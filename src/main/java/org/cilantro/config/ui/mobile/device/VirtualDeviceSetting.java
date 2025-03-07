

package org.cilantro.config.ui.mobile.device;

import lombok.Data;

/**
 * Virtual device specific settings
 *
 * @author Rahul Saini
 * @since 13-Dec-2024
 */
@Data
public class VirtualDeviceSetting {
    private boolean connectKeyboard = true;
    private boolean headless        = false;
    private int     launchTimeout   = 120;
    private String  name;
    private int     readyTimeout    = 60;
}
