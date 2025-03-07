

package org.cilantro.actions.interfaces.device;

import io.appium.java_client.android.nativekey.AndroidKey;

/**
 * Handles all Android device specific actions.
 *
 * @author Rahul Saini
 * @since 03-Jun-2024
 */
public interface IAndroidDeviceActions extends IDeviceActions {
    /**
     * Press Android key on the device.
     *
     * @param key Android key
     */
    void pressKey (AndroidKey key);
}
