package org.cilantro.manager;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.cilantro.manager.ParallelSession.getSession;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import org.cilantro.actions.interfaces.listeners.XchangeListener;
import org.cilantro.config.FrameworkSetting;
import org.cilantro.config.TestDataSetting;
import org.cilantro.config.api.ApiSetting;
import org.cilantro.config.ui.mobile.MobileSetting;
import org.cilantro.config.ui.web.WebSetting;
import org.cilantro.enums.ListenerType;
import org.cilantro.enums.Message;
import org.cilantro.enums.PlatformType;
import org.cilantro.utils.ErrorHandler;
import org.cilantro.utils.SettingUtils;
import lombok.Data;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Driver session class containing everything needed to handle current session.
 *
 * @param <D> {@link WebDriver}
 *
 * @author Rahul Saini
 * @since 19-Dec-2024
 */
@SuppressWarnings ("unchecked")
@Data
public class DriverSession<D extends WebDriver> {
    private static final ImmutableSet<ClassPath.ClassInfo> ALL_CLASSES = getAllClasses ();
    private static final Logger                            LOGGER      = getLogger ();

    private static ImmutableSet<ClassPath.ClassInfo> getAllClasses () {
        ImmutableSet<ClassPath.ClassInfo> result = null;
        try {
            result = ClassPath.from (ClassLoader.getSystemClassLoader ())
                .getAllClasses ();
        } catch (final IOException e) {
            ErrorHandler.handleAndThrow (Message.INVALID_LISTENER_FOUND, e);
        }
        return result;
    }

    private       String                                            configKey;
    private       D                                                   driver;
    private       Map<ListenerType, Class<? extends XchangeListener>> listeners;
    private       PlatformType                                        platformType;
    private       ServiceManager                                    serviceManager;
    private final FrameworkSetting                                  setting;
    private       Map<String, Object>                               sharedData;
    private       WebDriverWait                                     wait;

    /**
     * Driver session constructor.
     */
    DriverSession () {
        this.setting = SettingUtils.loadSetting ();
        this.sharedData = new HashMap<> ();
        this.listeners = new EnumMap<> (ListenerType.class);
        LOGGER.traceExit ();
    }

    /**
     * Clear all the listeners.
     */
    public void clearListeners () {
        this.listeners.clear ();
    }

    /**
     * Clears all the shared data for the session
     */
    public void clearSharedData () {
        this.sharedData.clear ();
    }

    /**
     * Gets API specific settings
     *
     * @return {@link ApiSetting} instance
     */
    public ApiSetting getApiSetting () {
        return this.setting.getApiSetting (this.configKey);
    }

    /**
     * Gets API specific settings
     *
     * @return {@link ApiSetting} instance
     */
    public ApiSetting getApiSetting (final String apiConfigKey) {
        if (getSession ().getConfigKey ()
            .contains ("test_restful")) {
            return this.setting.getApiSetting (this.configKey);
        } else {
            if (apiConfigKey.equals ("test_restful_agent")) {
                return this.setting.getUi ()
                    .getApiSetting ("test_restful_agent");
            } else if (apiConfigKey.equals ("test_restful_user")) {
                return this.setting.getUi ()
                    .getApiSetting ("test_restful_user");
            } else {
                return this.setting.getUi ()
                    .getApiSetting ("test_restful_admin");
            }
        }
    }

    /**
     * Gets the listener for provided listener type.
     *
     * @param listenerType {@link ListenerType}
     * @param <T> Type of the listener
     *
     * @return Listener object.
     */
    public <T extends XchangeListener> T getListener (final ListenerType listenerType) {
        T result = null;
        if (this.listeners.isEmpty ()) {
            loadAllListeners ();
        }
        final var listener = this.listeners.get (listenerType);
        if (isNull (listener)) {
            return null;
        }
        try {
            final var constructor = listener.getConstructor ();
            result = (T) constructor.newInstance ();
        } catch (final NoSuchMethodException | InstantiationException | IllegalAccessException |
                       InvocationTargetException e) {
            ErrorHandler.handleAndThrow (Message.INVALID_LISTENER_FOUND, e, listener);
        }
        return result;
    }

    /**
     * Gets Current Mobile settings
     *
     * @return Mobile setting
     */
    public MobileSetting getMobileSetting () {
        return this.setting.getUi ()
            .getMobileSetting (this.configKey);
    }

    /**
     * Gets the shared data.
     *
     * @param key Key of data to be retrieved
     * @param <T> Type of data
     *
     * @return Saved data
     */
    public <T> T getSharedData (final String key) {
        return (T) this.sharedData.get (key);
    }

    /**
     * Gets the test data settings.
     *
     * @return Test data setting.
     */
    public TestDataSetting getTestDataSetting () {
        return this.setting.getData ();
    }

    /**
     * Gets current Web settings
     *
     * @return Web Setting
     */
    public WebSetting getWebSetting () {
        return this.setting.getUi ()
            .getWebSetting (this.configKey);
    }

    /**
     * Removes the shared data.
     *
     * @param key Key to shared data
     */
    public void removeSharedData (final String key) {
        this.sharedData.remove (key);
    }

    /**
     * Save shared data.
     *
     * @param key Key of data
     * @param data Data to be saved
     * @param <T> Type of data
     */
    public <T> void setSharedData (final String key, final T data) {
        this.sharedData.put (key, data);
    }

    private void addListeners (final ArrayList<String> listenerList, final String packageName) {
        if (isNotEmpty (packageName)) {
            ALL_CLASSES.stream ()
                .filter (clazz -> clazz.getPackageName ()
                    .startsWith (packageName))
                .map (ClassPath.ClassInfo::getName)
                .forEach (listenerList::add);
        }
    }

    private void loadAllListeners () {
        final var packageName = getSetting ().getListenersPackage ();
        final var listenerList = new ArrayList<String> ();
        addListeners (listenerList, packageName);
        loadAllListeners (listenerList);
    }

    private <T extends XchangeListener> void loadAllListeners (final List<String> listenerList) {
        listenerList.forEach (listener -> {
            try {
                final var cls = (Class<T>) Class.forName (listener);
                final var interfaceType = cls.getInterfaces ()[0];
                final var listenerType = ListenerType.valueOf (interfaceType);
                final var isBoykaListener = XchangeListener.class.isAssignableFrom (cls);
                if (isBoykaListener && !this.listeners.containsKey (listenerType)) {
                    this.listeners.put (listenerType, cls);
                }
            } catch (final ClassNotFoundException e) {
                ErrorHandler.handleAndThrow (Message.INVALID_LISTENER_FOUND, e, listener);
            }
        });
    }
}
