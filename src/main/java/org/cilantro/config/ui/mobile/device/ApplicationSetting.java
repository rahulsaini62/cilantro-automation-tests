

package org.cilantro.config.ui.mobile.device;

import static org.cilantro.enums.ApplicationType.NATIVE;

import org.cilantro.enums.ApplicationType;
import org.cilantro.enums.Browser;
import lombok.Data;

/**
 * Application specific settings.
 *
 * @author Rahul Saini
 * @since 13-Dec-2024
 */
@Data
public class ApplicationSetting {
    private String  baseUrl;
    private Browser browser;
    private String  bundleId;
    private int             chromeDriverPort;
    private boolean         external;
    private int             installTimeout = 30;
    private String          path;
    private ApplicationType type           = NATIVE;
    private String          waitActivity;
    private int             waitTimeout    = 30;
}
