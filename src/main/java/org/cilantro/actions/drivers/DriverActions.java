package org.cilantro.actions.drivers;

import static org.cilantro.actions.CommonActions.getDriverAttribute;
import static org.cilantro.actions.CommonActions.performDriverAction;
import static org.cilantro.enums.ListenerType.DRIVER_ACTION;
import static org.cilantro.enums.Message.ERROR_CREATING_LOGS;
import static org.cilantro.enums.Message.ERROR_WHILE_SLEEPING;
import static org.cilantro.enums.Message.ERROR_WRITING_LOGS;
import static org.cilantro.enums.PlatformType.WEB;
import static org.cilantro.manager.ParallelSession.getSession;
import static org.cilantro.utils.ErrorHandler.handleAndThrow;
import static org.cilantro.utils.ErrorHandler.throwError;
import static java.lang.System.getProperty;
import static java.lang.System.lineSeparator;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static java.text.MessageFormat.format;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;

import org.cilantro.actions.interfaces.drivers.IDriverActions;
import org.cilantro.actions.interfaces.listeners.drivers.IDriverActionsListener;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.interactions.Actions;

/**
 * Device / Browser specific actions.
 *
 * @author Rahul Saini
 * @since 24-Dec-2024
 */
public final class DriverActions implements IDriverActions {
    private static final DriverActions DRIVER_ACTIONS = new DriverActions ();
    private static final Logger        LOGGER         = getLogger ();

    /**
     * Handles all other driver related actions
     *
     * @return {@link IDriverActions} instance object
     */
    public static IDriverActions withDriver () {
        return DRIVER_ACTIONS;
    }

    private final IDriverActionsListener listener;

    private DriverActions () {
        this.listener = getSession ().getListener (DRIVER_ACTION);
    }

    @Override
    @SuppressWarnings ("unchecked")
    public <T> T executeScript (final String script, final Object... args) {
        LOGGER.traceEntry ();
        LOGGER.info ("Executing script");
        ofNullable (this.listener).ifPresent (l -> l.onExecuteScript (script, args));
        return (T) getDriverAttribute (driver -> ((JavascriptExecutor) driver).executeScript (script, args), null);
    }

    @Override
    public void pause (final Duration time) {
        LOGGER.traceEntry ();
        ofNullable (this.listener).ifPresent (l -> l.onPause (time));
        performDriverAction (driver -> {
            if (getSession ().getPlatformType () == WEB) {
                final var action = new Actions (driver);
                action.pause (time)
                    .build ()
                    .perform ();
            } else {
                try {
                    sleep (time.toMillis ());
                } catch (final InterruptedException e) {
                    currentThread ().interrupt ();
                    throwError (ERROR_WHILE_SLEEPING);
                }
            }
        });
        LOGGER.traceExit ();
    }

    @Override
    public void saveLogs () {
        LOGGER.traceEntry ();
        ofNullable (this.listener).ifPresent (IDriverActionsListener::onSaveLogs);
        performDriverAction (d -> {
            final var logSetting = getSession ().getSetting ()
                .getUi ()
                .getLogging ();
            if (!logSetting.isEnable ()) {
                LOGGER.warn ("Cannot save different logs to file, logging is disabled...");
                return;
            }
            try {
                final var logTypes = d.manage ()
                    .logs ()
                    .getAvailableLogTypes ();
                logTypes.forEach (logType -> {
                    LOGGER.info ("Saving [{}] logs to a file...", logType);
                    if (isNull (logSetting.getExcludeLogs ()) || !logSetting.getExcludeLogs ()
                        .contains (logType)) {
                        saveLogType (d, logType, logSetting.getPath ());
                    } else {
                        LOGGER.info ("Skipped saving [{}] logs to a file...", logType);
                    }
                });
            } catch (final WebDriverException e) {
                LOGGER.catching (e);
                LOGGER.warn ("Error while saving different logs: {}", e.getMessage ());
            }
        });
        LOGGER.traceExit ();
    }

    @Override
    public <T> void waitUntil (final Function<WebDriver, T> condition) {
        LOGGER.traceEntry ();
        LOGGER.info ("Waiting for condition...");
        ofNullable (this.listener).ifPresent (IDriverActionsListener::onWaitUntil);
        performDriverAction (driver -> {
            final var wait = getSession ().getWait ();
            wait.until (condition);
        });
    }

    private <D extends WebDriver> void saveLogType (final D driver, final String logType, final String logPath) {
        final var logEntries = driver.manage ()
            .logs ()
            .get (logType);
        final var fileName = format ("{0}/{1}/{2}-{3}.log", getProperty ("user.dir"), logPath, logType,
            currentThread ().getId ());
        try (final var writer = new BufferedWriter (new FileWriter (fileName))) {
            logEntries.forEach (logEntry -> {
                try {
                    writer.write (logEntry.getMessage ());
                    writer.write (lineSeparator ());
                } catch (final IOException e) {
                    handleAndThrow (ERROR_WRITING_LOGS, e);
                }
            });
        } catch (final IOException e) {
            handleAndThrow (ERROR_CREATING_LOGS, e);
        }
    }
}
