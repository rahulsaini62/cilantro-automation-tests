package org.cilantro.enums;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.AutomationName.IOS_XCUI_TEST;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Automation types for Mobile.
 *
 * @author Rahul Saini
 * @since 06-Dec-2024
 */
@AllArgsConstructor
@Getter
public enum AutomationType {
    /**
     * Android Ui Automator 2 driver.
     */
    UI_AUTOMATOR ("uiautomator2", ANDROID_UIAUTOMATOR2),
    /**
     * iOS XCUITest driver.
     */
    XCUI ("xcuitest", IOS_XCUI_TEST);

    private final String driverName;
    private final String name;
}
