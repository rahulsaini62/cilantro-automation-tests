package org.cilantro.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Appium server log lever.
 *
 * @author Rahul Saini
 * @since 07-Dec-2024
 */
@ToString
@AllArgsConstructor
@Getter
public enum LogLevel {
    /**
     * Debug.
     */
    DEBUG ("debug"),
    /**
     * Debug+Debug
     */
    DEBUG_DEBUG ("debug:debug"),
    /**
     * Debug+Error
     */
    DEBUG_ERROR ("debug:error"),
    /**
     * Debug+Info
     */
    DEBUG_INFO ("debug:info"),
    /**
     * Debug+Warn
     */
    DEBUG_WARN ("debug:warn"),
    /**
     * Error
     */
    ERROR ("error"),
    /**
     * Error+Debug
     */
    ERROR_DEBUG ("error:debug"),
    /**
     * Error+Error
     */
    ERROR_ERROR ("error:error"),
    /**
     * Error+Info
     */
    ERROR_INFO ("error:info"),
    /**
     * Error+Warn
     */
    ERROR_WARN ("error:warn"),
    /**
     * Info
     */
    INFO ("info"),
    /**
     * Info+Debug
     */
    INFO_DEBUG ("info:debug"),
    /**
     * Info+Error
     */
    INFO_ERROR ("info:error"),
    /**
     * Info+Info
     */
    INFO_INFO ("info:info"),
    /**
     * Info+Warn
     */
    INFO_WARN ("info:warn"),
    /**
     * Warn
     */
    WARN ("warn"),
    /**
     * Warn+Debug
     */
    WARN_DEBUG ("warn:debug"),
    /**
     * Warn+Error
     */
    WARN_ERROR ("warn:error"),
    /**
     * Warn+Info
     */
    WARN_INFO ("warn:info"),
    /**
     * Warn+Warn
     */
    WARN_WARN ("warn:warn");

    private final String level;
}
