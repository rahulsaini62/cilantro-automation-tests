package org.cilantro.actions.drivers;

import static org.cilantro.actions.CommonActions.getDriverAttribute;
import static org.cilantro.actions.CommonActions.performDriverAction;
import static org.cilantro.actions.drivers.DriverActions.withDriver;
import static org.cilantro.enums.ApplicationType.HYBRID;
import static org.cilantro.enums.ListenerType.CONTEXT_ACTION;
import static org.cilantro.enums.Message.CONTEXT_SWITCHING_NOT_ALLOWED;
import static org.cilantro.manager.ParallelSession.getSession;
import static org.cilantro.utils.ErrorHandler.throwError;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.ArrayList;
import java.util.List;

import io.appium.java_client.remote.SupportsContextSwitching;
import org.cilantro.actions.interfaces.drivers.IContextActions;
import org.cilantro.actions.interfaces.listeners.drivers.IContextActionsListener;
import org.apache.logging.log4j.Logger;

/**
 * Handles Contexts
 *
 * @author Rahul Saini
 * @since 09-Dec-2024
 */
public class ContextActions implements IContextActions {
    private static final IContextActions CONTEXT_ACTIONS = new ContextActions ();
    private static final Logger          LOGGER          = getLogger ();

    /**
     * Handles all context related actions
     *
     * @return {@link IContextActions} instance object
     */
    public static IContextActions withContext () {
        return CONTEXT_ACTIONS;
    }

    private final IContextActionsListener listener;

    private ContextActions () {
        this.listener = getSession ().getListener (CONTEXT_ACTION);
    }

    @Override
    public List<String> contexts () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting All available context names...");
        ofNullable (this.listener).ifPresent (IContextActionsListener::onContexts);
        return getDriverAttribute ((final SupportsContextSwitching d) -> new ArrayList<> (d.getContextHandles ()),
            null);
    }

    @Override
    public String currentContext () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting current context name...");
        ofNullable (this.listener).ifPresent (IContextActionsListener::onCurrentContext);
        return getDriverAttribute (SupportsContextSwitching::getContext, null);
    }

    @Override
    public void switchToNative () {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching context to NATIVE_APP...");
        ofNullable (this.listener).ifPresent (IContextActionsListener::onSwitchToNative);
        switchToWebView ("NATIVE_APP");
        LOGGER.traceExit ();
    }

    @Override
    public void switchToWebView (final String contextName) {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching context to [{}]...", contextName);
        ofNullable (this.listener).ifPresent (l -> l.onSwitchToWebView (contextName));
        final var applicationType = getSession ().getMobileSetting ()
            .getDevice ()
            .getApplication ()
            .getType ();
        if (applicationType != HYBRID) {
            throwError (CONTEXT_SWITCHING_NOT_ALLOWED, applicationType);
        }
        performDriverAction ((SupportsContextSwitching d) -> d.context (contextName));
        LOGGER.traceExit ();
    }

    @Override
    public void switchToWebView () {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching context to first Web view...");
        ofNullable (this.listener).ifPresent (IContextActionsListener::onSwitchToWebView);
        withDriver ().waitUntil (d -> contexts ().size () > 1);
        final var webContext = contexts ().stream ()
            .filter (c -> !c.equals ("NATIVE_APP"))
            .findFirst ();
        webContext.ifPresent (this::switchToWebView);
        LOGGER.traceExit ();
    }
}
