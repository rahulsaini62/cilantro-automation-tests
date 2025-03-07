package org.cilantro.manager;

import static java.lang.String.format;
import static org.cilantro.enums.TargetProviders.LOCAL;
import static java.lang.System.getProperty;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.remote.options.SupportsAppOption;
import org.cilantro.actions.drivers.NavigateActions;
import org.cilantro.config.ui.mobile.device.ApplicationSetting;
import org.cilantro.enums.ApplicationType;
import org.cilantro.enums.TargetProviders;
import org.cilantro.utils.StringUtils;
import org.openqa.selenium.MutableCapabilities;

interface IDriverManager {
    default void navigateToBaseUrl (final ApplicationSetting application) {
        if (application.getType () == ApplicationType.WEB && isNotEmpty (application.getBaseUrl ())) {
            NavigateActions.navigate ()
                .to (application.getBaseUrl ());
        }
    }

    default <E extends BaseOptions<E>, T extends SupportsAppOption<E>> void setupApplicationOptions (
        final ApplicationSetting application, final T options) {
        if (isNotEmpty (application.getPath ())) {
            if (!application.isExternal ()) {
                options.setApp (Path.of (getProperty ("user.dir"), "/src/test/resources", application.getPath ())
                    .toString ());
            } else {
                options.setApp (StringUtils.interpolate (application.getPath ()));
            }
        }
    }

    default <E extends MutableCapabilities> void setupCloudDriverOptions (final E options,
        final Map<String, Object> capabilities, final TargetProviders targetProviders) {
        if (!isNull (capabilities) && targetProviders != LOCAL) {
            final var optionCapabilities = new HashMap<String, Object> ();
            capabilities.forEach ((k, v) -> {
                if (v instanceof String) {
                    optionCapabilities.put (k, StringUtils.interpolate (v.toString ()));
                } else {
                    optionCapabilities.put (k, v);
                }
            });
            options.setCapability (format ("{0}:options", targetProviders.getPrefix ()), optionCapabilities);
        }
    }

    void setupDriver ();
}
