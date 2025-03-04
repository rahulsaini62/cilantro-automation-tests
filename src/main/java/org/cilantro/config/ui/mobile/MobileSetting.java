

package org.cilantro.config.ui.mobile;

import org.cilantro.config.ui.mobile.device.DeviceSetting;
import org.cilantro.config.ui.mobile.server.ServerSetting;
import lombok.Data;

/**
 * Mobile settings.
 *
 * @author Rahul Saini
 * @since 06-Dec-2024
 */
@Data
public class MobileSetting {
    private DeviceSetting device = new DeviceSetting ();
    private ServerSetting server = new ServerSetting ();
}
