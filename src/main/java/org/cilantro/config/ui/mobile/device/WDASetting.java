

package org.cilantro.config.ui.mobile.device;

import lombok.Data;

/**
 * iOS WebDriverAgent related settings.
 *
 * @author Rahul Saini
 * @since 26-Dec-2024
 */
@Data
public class WDASetting {
    private int     connectionTimeout    = 60;
    private int     launchTimeout        = 60;
    private int     localPort            = 8100;
    private String  signingId;
    private int     startupRetries       = 2;
    private int     startupRetryInterval = 10;
    private String  teamId;
    private String  updateBundleId;
    private boolean useNew;
    private boolean usePrebuilt;
}
