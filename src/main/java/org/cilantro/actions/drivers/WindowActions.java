package org.cilantro.actions.drivers;

import static com.google.common.truth.Truth.assertWithMessage;
import static org.cilantro.actions.CommonActions.getDriverAttribute;
import static org.cilantro.actions.CommonActions.performDriverAction;
import static org.cilantro.enums.ListenerType.WINDOW_ACTION;
import static org.cilantro.enums.Message.DRIVER_CANNOT_BE_NULL;
import static org.cilantro.enums.Message.ERROR_SAVING_SCREENSHOT;
import static org.cilantro.manager.ParallelSession.getSession;
import static org.cilantro.utils.ErrorHandler.handleAndThrow;
import static org.cilantro.utils.Validator.requireNonNull;
import static java.util.Calendar.getInstance;
import static java.util.Optional.ofNullable;
import static org.apache.commons.io.FileUtils.copyFile;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.openqa.selenium.OutputType.FILE;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.common.truth.StringSubject;
import org.cilantro.actions.interfaces.drivers.IWindowActions;
import org.cilantro.actions.interfaces.listeners.drivers.IWindowActionsListener;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

/**
 * Handles all windows related actions
 *
 * @author Rahul Saini
 * @since 16-Dec-2024
 */
public class WindowActions implements IWindowActions {
    private static final Logger         LOGGER         = getLogger ();
    private static final IWindowActions WINDOW_ACTIONS = new WindowActions ();

    /**
     * Handles windows related actions
     *
     * @return {@link IWindowActions} instance object
     */
    public static IWindowActions onWindow () {
        return WINDOW_ACTIONS;
    }

    private final IWindowActionsListener listener;

    private WindowActions () {
        this.listener = getSession ().getListener (WINDOW_ACTION);
    }

    @Override
    public void close () {
        LOGGER.traceEntry ();
        LOGGER.info ("Closing window...");
        ofNullable (this.listener).ifPresent (IWindowActionsListener::onClose);
        performDriverAction (WebDriver::close);
        switchToDefault ();
        LOGGER.traceExit ();
    }

    @Override
    public String currentHandle () {
        LOGGER.traceEntry ();
        LOGGER.info ("Gets the current window handle...");
        ofNullable (this.listener).ifPresent (IWindowActionsListener::onCurrentHandle);
        final var handle = getDriverAttribute (WebDriver::getWindowHandle, EMPTY);
        LOGGER.traceExit ();
        return handle;
    }

    @Override
    public void fullScreen () {
        LOGGER.traceEntry ();
        LOGGER.info ("Making the window full screen...");
        ofNullable (this.listener).ifPresent (IWindowActionsListener::onFullScreen);
        performDriverAction (driver -> driver.manage ()
            .window ()
            .fullscreen ());
        LOGGER.traceExit ();
    }

    @Override
    public String getTitle () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting title of the browser...");
        ofNullable (this.listener).ifPresent (IWindowActionsListener::onGetTitle);
        return LOGGER.traceExit (getDriverAttribute (WebDriver::getTitle, EMPTY));
    }

    @Override
    public List<String> handles () {
        LOGGER.traceEntry ();
        LOGGER.info ("Gets all the available window handles...");
        ofNullable (this.listener).ifPresent (IWindowActionsListener::onHandles);
        final var handles = getDriverAttribute (WebDriver::getWindowHandles, new ArrayList<String> ());
        LOGGER.traceExit ();
        return new ArrayList<> (handles);
    }

    @Override
    public void maximize () {
        LOGGER.traceEntry ();
        LOGGER.info ("Maximize the window...");
        ofNullable (this.listener).ifPresent (IWindowActionsListener::onMaximize);
        performDriverAction (driver -> driver.manage ()
            .window ()
            .maximize ());
        LOGGER.traceExit ();
    }

    @Override
    public void minimize () {
        LOGGER.traceEntry ();
        LOGGER.info ("Minimize the window...");
        ofNullable (this.listener).ifPresent (IWindowActionsListener::onMinimize);
        performDriverAction (driver -> driver.manage ()
            .window ()
            .minimize ());
        LOGGER.traceExit ();
    }

    @Override
    public void switchTo (final String nameOrHandle) {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching to window: [{}]...", nameOrHandle);
        ofNullable (this.listener).ifPresent (l -> l.onSwitchTo (nameOrHandle));
        performDriverAction (driver -> driver.switchTo ()
            .window (nameOrHandle));
        LOGGER.traceExit ();
    }

    @Override
    public void switchToDefault () {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching to main window...");
        ofNullable (this.listener).ifPresent (IWindowActionsListener::onSwitchToDefault);
        performDriverAction (driver -> driver.switchTo ()
            .window (handles ().get (0)));
        LOGGER.traceExit ();
    }

    @Override
    public void switchToNew (final WindowType type) {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching to new [{}]...", type);
        ofNullable (this.listener).ifPresent (l -> l.onSwitchToNew (type));
        performDriverAction (driver -> driver.switchTo ()
            .newWindow (type));
        LOGGER.traceExit ();
    }

    @Override
    public void takeScreenshot () {
        LOGGER.info ("Taking the screenshot...");
        final var setting = getSession ().getSetting ()
            .getUi ()
            .getScreenshot ();
        if (!setting.isEnabled ()) {
            return;
        }
        final var path = setting.getPath ();
        final var prefix = setting.getPrefix ();
        final var extension = setting.getExtension ();
        final var date = new SimpleDateFormat ("yyyyMMdd-HHmmss");
        final var timeStamp = date.format (getInstance ().getTime ());
        final var fileName = "%s/%s-%s.%s";
        takeScreenshot (String.format (fileName, path, prefix, timeStamp, extension));
    }

    @Override
    public void takeScreenshot (final String fileName) {
        LOGGER.traceEntry ();
        LOGGER.info ("Taking screenshot and saving at [{}]...", fileName);
        final var setting = getSession ().getSetting ()
            .getUi ()
            .getScreenshot ();
        if (!setting.isEnabled ()) {
            return;
        }
        performDriverAction (driver -> {
            final var file = ((TakesScreenshot) requireNonNull (driver, DRIVER_CANNOT_BE_NULL)).getScreenshotAs (FILE);
            try {
                copyFile (file, new File (fileName));
            } catch (final IOException e) {
                handleAndThrow (ERROR_SAVING_SCREENSHOT, e);
            }
        });
        ofNullable (this.listener).ifPresent (l -> l.onTakeScreenshot (fileName));
        LOGGER.traceExit ();
    }

    @Override
    public StringSubject verifyTitle () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying browser title...");
        ofNullable (this.listener).ifPresent (IWindowActionsListener::onVerifyTitle);
        LOGGER.traceExit ();
        return assertWithMessage ("Title").that (getTitle ());
    }

    @Override
    public Dimension viewportSize () {
        LOGGER.info ("Getting Viewport of the screen...");
        ofNullable (this.listener).ifPresent (IWindowActionsListener::onViewportSize);
        return getDriverAttribute (driver -> driver.manage ()
            .window ()
            .getSize (), new Dimension (0, 0));
    }
}
