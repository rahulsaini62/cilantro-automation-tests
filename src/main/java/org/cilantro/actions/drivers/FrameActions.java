package org.cilantro.actions.drivers;

import static org.cilantro.actions.CommonActions.performDriverAction;
import static org.cilantro.enums.ListenerType.FRAME_ACTION;
import static org.cilantro.manager.ParallelSession.getSession;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import org.cilantro.actions.interfaces.drivers.IFrameActions;
import org.cilantro.actions.interfaces.listeners.drivers.IFrameActionsListener;
import org.apache.logging.log4j.Logger;

/**
 * Handle all frame related actions
 *
 * @author Rahul Saini
 * @since 16-Dec-2024
 */
public class FrameActions implements IFrameActions {
    private static final IFrameActions FRAME_ACTIONS = new FrameActions ();
    private static final Logger        LOGGER        = getLogger ();

    /**
     * Handles all frames related actions
     *
     * @return {@link IFrameActions} instance object
     */
    public static IFrameActions onFrame () {
        return FRAME_ACTIONS;
    }

    private final IFrameActionsListener listener;

    private FrameActions () {
        this.listener = getSession ().getListener (FRAME_ACTION);
    }

    @Override
    public void switchTo (final String frameName) {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching to frame: {}", frameName);
        ofNullable (this.listener).ifPresent (l -> l.onSwitchTo (frameName));
        performDriverAction (driver -> driver.switchTo ()
            .frame (frameName));
        LOGGER.traceExit ();
    }

    @Override
    public void switchTo (final int index) {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching to frame index: {}", index);
        ofNullable (this.listener).ifPresent (l -> l.onSwitchTo (index));
        performDriverAction (driver -> driver.switchTo ()
            .frame (index));
        LOGGER.traceExit ();
    }

    @Override
    public void switchToParent () {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching to main frame...");
        ofNullable (this.listener).ifPresent (IFrameActionsListener::onSwitchToParent);
        performDriverAction (driver -> driver.switchTo ()
            .parentFrame ());
        LOGGER.traceExit ();
    }
}
