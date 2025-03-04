package org.cilantro.enums;

import java.util.Arrays;

import org.cilantro.actions.interfaces.listeners.XchangeListener;
import org.cilantro.actions.interfaces.listeners.api.IApiActionsListener;
import org.cilantro.actions.interfaces.listeners.data.ITestDataActionsListener;
import org.cilantro.actions.interfaces.listeners.device.IAndroidDeviceActionsListener;
import org.cilantro.actions.interfaces.listeners.device.IDeviceActionsListener;
import org.cilantro.actions.interfaces.listeners.drivers.IAlertActionsListener;
import org.cilantro.actions.interfaces.listeners.drivers.IContextActionsListener;
import org.cilantro.actions.interfaces.listeners.drivers.ICookieActionsListener;
import org.cilantro.actions.interfaces.listeners.drivers.IDriverActionsListener;
import org.cilantro.actions.interfaces.listeners.drivers.IFrameActionsListener;
import org.cilantro.actions.interfaces.listeners.drivers.INavigateActionsListener;
import org.cilantro.actions.interfaces.listeners.drivers.IWindowActionsListener;
import org.cilantro.actions.interfaces.listeners.elements.IClickableActionsListener;
import org.cilantro.actions.interfaces.listeners.elements.IDropDownActionsListener;
import org.cilantro.actions.interfaces.listeners.elements.IElementActionsListener;
import org.cilantro.actions.interfaces.listeners.elements.IFingerActionsListener;
import org.cilantro.actions.interfaces.listeners.elements.IFingersActionsListener;
import org.cilantro.actions.interfaces.listeners.elements.ITextBoxActionsListener;
import lombok.Getter;

/**
 * List all the available listener types in the framework.
 *
 * @author Rahul Saini
 * @since 07-Dec-2024
 */
@Getter
public enum ListenerType {
    /**
     * Alert Actions listener.
     */
    ALERT_ACTION (IAlertActionsListener.class),
    /**
     * Android device action listener.
     */
    ANDROID_DEVICE_ACTION (IAndroidDeviceActionsListener.class),
    /**
     * API actions listener.
     */
    API_ACTION (IApiActionsListener.class),
    /**
     * Mouse actions listener.
     */
    CLICKABLE_ACTION (IClickableActionsListener.class),
    /**
     * Context actions listener.
     */
    CONTEXT_ACTION (IContextActionsListener.class),
    /**
     * Cookies action listener.
     */
    COOKIE_ACTION (ICookieActionsListener.class),
    /**
     * Device action listener.
     */
    DEVICE_ACTION (IDeviceActionsListener.class),
    /**
     * Driver action listener.
     */
    DRIVER_ACTION (IDriverActionsListener.class),
    /**
     * Drop down action listener.
     */
    DROP_DOWN_ACTION (IDropDownActionsListener.class),
    /**
     * Element action listener.
     */
    ELEMENT_ACTION (IElementActionsListener.class),
    /**
     * Fingers action listener.
     */
    FINGERS_ACTION (IFingersActionsListener.class),
    /**
     * Finger action listener.
     */
    FINGER_ACTION (IFingerActionsListener.class),
    /**
     * Frame action listener.
     */
    FRAME_ACTION (IFrameActionsListener.class),
    /**
     * Navigate action listener.
     */
    NAVIGATE_ACTION (INavigateActionsListener.class),
    /**
     * Test data action listener.
     */
    TEST_DATA_ACTION (ITestDataActionsListener.class),
    /**
     * Text Box action listener.
     */
    TEXT_BOX_ACTION (ITextBoxActionsListener.class),
    /**
     * Window action listener.
     */
    WINDOW_ACTION (IWindowActionsListener.class);

    /**
     * Convert provided listener class to Listener type enum.
     *
     * @param className Class name of probable listener interface
     *
     * @return Listener type item.
     */
    public static ListenerType valueOf (final Class<?> className) {
        return Arrays.stream (values ())
            .filter (l -> l.getClassName ()
                .equals (className))
            .findFirst ()
            .orElse (null);
    }

    private final Class<? extends XchangeListener> className;

    ListenerType (final Class<? extends XchangeListener> className) {
        this.className = className;
    }
}
