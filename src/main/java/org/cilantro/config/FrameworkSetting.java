package org.cilantro.config;

import static org.cilantro.enums.Message.CONFIG_KEY_NOT_FOUND;
import static org.cilantro.enums.Message.NO_API_SETTINGS_FOUND;
import static org.cilantro.utils.ErrorHandler.throwError;
import static org.cilantro.utils.Validator.requireNonNull;
import static java.lang.String.join;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.Map;

import org.cilantro.config.api.ApiSetting;
import org.cilantro.config.ui.UISetting;
import lombok.Data;
import org.apache.logging.log4j.Logger;

/**
 * Framework setting.
 *
 * @author Rahul Saini
 * @since 17-Dec-2024
 */
@Data
public class FrameworkSetting {
    private static final Logger LOGGER = getLogger ();

    private Map<String, ApiSetting> api;
    private TestDataSetting         data = new TestDataSetting ();
    private String                  listenersPackage;
    private UISetting               ui;

    /**
     * Get API setting.
     *
     * @param key API config key
     *
     * @return {@link ApiSetting} instance
     */
    public ApiSetting getApiSetting (final String key) {
        LOGGER.traceEntry ("Key: {}", key);
        if (!this.api.containsKey (key)) {
            final var keys = join (", ", this.api.keySet ());
            throwError (CONFIG_KEY_NOT_FOUND, key, keys);
        }
        return LOGGER.traceExit (requireNonNull (this.api.get (key), NO_API_SETTINGS_FOUND, key));
    }
}
