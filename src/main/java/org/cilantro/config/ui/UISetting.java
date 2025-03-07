package org.cilantro.config.ui;

import static org.cilantro.utils.SettingUtils.getSetting;

import java.util.Map;

import org.cilantro.config.api.ApiSetting;
import org.cilantro.config.ui.mobile.MobileSetting;
import org.cilantro.config.ui.web.WebSetting;
import lombok.Data;
import org.cilantro.utils.SettingUtils;

/**
 * @author Rahul Saini
 * @since 17-Dec-2024
 */
@Data
public class UISetting {
    private DelaySetting               delay      = new DelaySetting ();
    private LogSetting                 logging    = new LogSetting ();
    private Map<String, MobileSetting> mobile;
    private ScreenshotSetting          screenshot = new ScreenshotSetting ();
    private TimeoutSetting             timeout    = new TimeoutSetting ();
    private Map<String, WebSetting>    web;
    private Map<String, ApiSetting>    api;

    /**
     * Get Mobile settings.
     *
     * @param key config key for mobile
     *
     * @return the {@link MobileSetting}
     */
    public MobileSetting getMobileSetting (final String key) {
        return getSetting (this.mobile, key);
    }

    /**
     * Gets the web setting.
     *
     * @param key the config key for Web
     *
     * @return the {@link WebSetting}
     */
    public WebSetting getWebSetting (final String key) {
        return getSetting (this.web, key);
    }

    /**
     * Gets the service setting.
     *
     * @param key the config key for Web
     *
     * @return the {@link ApiSetting}
     */
    public ApiSetting getApiSetting (final String key) {
        return SettingUtils.getSetting (this.api, key);
    }
}
