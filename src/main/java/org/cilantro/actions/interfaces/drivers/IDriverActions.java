

package org.cilantro.actions.interfaces.drivers;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.WebDriver;

/**
 * All other driver related actions
 *
 * @author Rahul Saini
 * @since 16-Dec-2024
 */
public interface IDriverActions {
    /**
     * Executes javascript in browser.
     *
     * @param script Javascript to execute
     * @param args Arguments to pass to javascript
     * @param <T> Return type of the result of the script
     *
     * @return the result of the script
     */
    <T> T executeScript (final String script, final Object... args);

    /**
     * Pause for the specified time.
     *
     * @param time Duration to pause.
     */
    void pause (final Duration time);

    /**
     * Save all the available logs to files in `logs` folder.
     */
    void saveLogs ();

    /**
     * Wait for a specific condition to be true.
     *
     * @param condition condition to wait for
     * @param <T> type of condition
     */
    <T> void waitUntil (final Function<WebDriver, T> condition);
}
