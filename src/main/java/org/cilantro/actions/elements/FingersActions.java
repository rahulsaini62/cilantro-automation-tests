package org.cilantro.actions.elements;

import static org.cilantro.actions.CommonActions.getDriverAttribute;
import static org.cilantro.actions.CommonActions.performMobileGestures;
import static org.cilantro.enums.ListenerType.FINGERS_ACTION;
import static org.cilantro.enums.SwipeDirection.LEFT;
import static org.cilantro.enums.SwipeDirection.RIGHT;
import static org.cilantro.manager.ParallelSession.getSession;
import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import org.cilantro.actions.interfaces.elements.IFingersActions;
import org.cilantro.actions.interfaces.listeners.elements.IFingersActionsListener;
import org.cilantro.builders.Locator;
import org.apache.logging.log4j.Logger;

/**
 * Handles all multi-fingers related actions
 *
 * @author Rahul Saini
 * @since 15-Dec-2024
 */
public class FingersActions extends FingerActions implements IFingersActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Handles all multi-fingers related actions
     *
     * @param locator Locator of the element
     *
     * @return {@link IFingersActions} instance object
     */
    public static IFingersActions withFingers (final Locator locator) {
        return new FingersActions (locator);
    }

    private final IFingersActionsListener listener;

    FingersActions (final Locator locator) {
        super (locator);
        this.listener = getSession ().getListener (FINGERS_ACTION);
    }

    @Override
    public void zoomIn () {
        LOGGER.traceEntry ();
        LOGGER.info ("Zooming in on the element [{}].", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onZoomIn (this.locator));
        final var finger1 = getDriverAttribute (driver -> FingerGestureBuilder.init ()
            .direction (LEFT)
            .name ("Finger 1")
            .sourceElement (this.locator)
            .offset (10)
            .build ()
            .swipe (), null);
        final var finger2 = getDriverAttribute (driver -> FingerGestureBuilder.init ()
            .direction (RIGHT)
            .name ("Finger 2")
            .sourceElement (this.locator)
            .offset (10)
            .build ()
            .swipe (), null);
        performMobileGestures (asList (finger1, finger2));
        LOGGER.traceExit ();
    }

    @Override
    public void zoomOut () {
        LOGGER.traceEntry ();
        LOGGER.info ("Zooming out on the element [{}].", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onZoomOut (this.locator));
        final var finger1 = getDriverAttribute (driver -> FingerGestureBuilder.init ()
            .direction (LEFT)
            .name ("Finger 1")
            .sourceElement (this.locator)
            .reverse (true)
            .offset (10)
            .build ()
            .swipe (), null);
        final var finger2 = getDriverAttribute (driver -> FingerGestureBuilder.init ()
            .direction (RIGHT)
            .name ("Finger 2")
            .sourceElement (this.locator)
            .reverse (true)
            .offset (10)
            .build ()
            .swipe (), null);
        performMobileGestures (asList (finger1, finger2));
        LOGGER.traceExit ();
    }
}
