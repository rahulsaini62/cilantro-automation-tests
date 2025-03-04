

package org.cilantro.actions.interfaces.drivers;

import java.util.List;

import com.google.common.truth.StringSubject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WindowType;

/**
 * All window related actions
 *
 * @author Rahul Saini
 * @since 16-Dec-2024
 */
public interface IWindowActions {
    /**
     * Close browser window.
     */
    void close ();

    /**
     * Gets the current window handle.
     *
     * @return the current window handle
     */
    String currentHandle ();

    /**
     * Switch browser window to full screen.
     */
    void fullScreen ();

    /**
     * Title of the browser.
     *
     * @return title of the browser
     */
    String getTitle ();

    /**
     * Gets all open window handles.
     *
     * @return all open window handles
     */
    List<String> handles ();

    /**
     * Makes browser window maximized.
     */
    void maximize ();

    /**
     * Makes browser window minimized.
     */
    void minimize ();

    /**
     * Switch to window for specific name / handle.
     *
     * @param nameOrHandle name or handle of the window
     */
    void switchTo (final String nameOrHandle);

    /**
     * Switch to default first window.
     */
    void switchToDefault ();

    /**
     * Switch to new window.
     *
     * @param type type of window
     */
    void switchToNew (final WindowType type);

    /**
     * Takes screenshot of browser.
     */
    void takeScreenshot ();

    /**
     * Take screenshot of browser.
     *
     * @param fileName file name
     */
    void takeScreenshot (final String fileName);

    /**
     * Verify browser title.
     *
     * @return {@link StringSubject} to verify browser title
     */
    StringSubject verifyTitle ();

    /**
     * Gets the window / device screen size.
     *
     * @return Size of the window screen
     */
    Dimension viewportSize ();
}
