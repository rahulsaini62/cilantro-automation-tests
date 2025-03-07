

package org.cilantro.config.ui.mobile.device;

import lombok.Data;

/**
 * Video recording related settings.
 *
 * @author Rahul Saini
 * @since 13-Dec-2024
 */
@Data
public class VideoSetting {
    private AndroidVideoSetting android = new AndroidVideoSetting ();
    private boolean             enabled = false;
    private IOSVideoSetting     ios     = new IOSVideoSetting ();
    private String              path    = "./videos";
    private String              prefix  = "VID";
    private String              size;
    private int                 timeLimit;
}
