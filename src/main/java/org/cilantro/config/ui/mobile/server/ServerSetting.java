

package org.cilantro.config.ui.mobile.server;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.cilantro.enums.AutomationType;
import org.cilantro.enums.Protocol;
import org.cilantro.enums.TargetProviders;
import org.cilantro.utils.StringUtils;
import lombok.Data;

/**
 * Appium Server settings.
 *
 * @author Rahul Saini
 * @since 06-Dec-2024
 */
@Data
public class ServerSetting {
    private boolean             allowCors;
    private List<String>        allowInsecure;
    private String              appiumPath;
    private String              basePath;
    private String              callbackAddress;
    private int                 callbackPort;
    private String              configPath;
    private List<String>        denyInsecure;
    private AutomationType      driver;
    private boolean             external;
    private boolean             externalConfig;
    private String              host;
    private int                 keepAliveTimeout;
    private LogSetting          logging;
    private String              nodePath;
    private Map<String, Object> otherArgs;
    private String              password;
    private List<String>        plugins;
    private int                 port;
    private Protocol            protocol;
    private boolean             relaxedSecurity;
    private boolean             sessioDecerride;
    private boolean             strictCapabilities;
    private TargetProviders     target  = TargetProviders.LOCAL;
    private int                 timeout = 30;
    private String              userName;
    private String              webhook;

    /**
     * Gets the Appium Config path
     *
     * @return Path to Appium Config.
     */
    public String getConfigPath () {
        if (isExternalConfig () || isEmpty (this.configPath)) {
            return this.configPath;
        }
        return Path.of (System.getProperty ("user.dir"), this.configPath)
            .toString ();
    }

    /**
     * Gets cloud password.
     *
     * @return the cloud password
     */
    public String getPassword () {
        return StringUtils.interpolate (this.password);
    }

    /**
     * Gets cloud user name.
     *
     * @return the cloud username.
     */
    public String getUserName () {
        return StringUtils.interpolate (this.userName);
    }
}
