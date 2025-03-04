package org.cilantro.actions.elements;

import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.cilantro.actions.CommonActions.getDriverAttribute;
import static org.cilantro.actions.CommonActions.performMobileGestures;
import static org.cilantro.enums.ListenerType.FINGERS_ACTION;
import static org.cilantro.enums.SwipeDirection.LEFT;
import static org.cilantro.enums.SwipeDirection.RIGHT;
import static org.cilantro.manager.ParallelSession.getSession;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.cilantro.actions.interfaces.elements.IFingersActions;
import org.cilantro.actions.interfaces.listeners.elements.IFingersActionsListener;

public class WFingersActions extends WFingerActions implements IFingersActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Handles all multi-fingers related actions
     *
     * @param element Locator of the element
     *
     * @return {@link IFingersActions} instance object
     */
    public static IFingersActions withFingers (final WebElement element) {
        return new WFingersActions (element);
    }

    private final IFingersActionsListener listener;

    WFingersActions (final WebElement element) {
        super (element);
        this.listener = getSession ().getListener (FINGERS_ACTION);
    }

    /**
     * Zoom in on the screen.
     */
    @Override
    public void zoomIn () {
        LOGGER.traceEntry ();
        LOGGER.info ("Zooming in on the element [{}].", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onZoomIn (this.element));
        final var finger1 = getDriverAttribute (driver -> WFingerGestureBuilder.init ()
            .direction (LEFT)
            .name ("Finger 1")
            .sourceElement (this.element)
            .offset (10)
            .build ()
            .swipe (), null);
        final var finger2 = getDriverAttribute (driver -> WFingerGestureBuilder.init ()
            .direction (RIGHT)
            .name ("Finger 2")
            .sourceElement (this.element)
            .offset (10)
            .build ()
            .swipe (), null);
        performMobileGestures (asList (finger1, finger2));
        LOGGER.traceExit ();
    }

    /**
     * Zoom out of the screen.
     */
    @Override
    public void zoomOut () {
        LOGGER.traceEntry ();
        LOGGER.info ("Zooming out on the element [{}].", this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onZoomOut (this.element));
        final var finger1 = getDriverAttribute (driver -> WFingerGestureBuilder.init ()
            .direction (LEFT)
            .name ("Finger 1")
            .sourceElement (this.element)
            .reverse (true)
            .offset (10)
            .build ()
            .swipe (), null);
        final var finger2 = getDriverAttribute (driver -> WFingerGestureBuilder.init ()
            .direction (RIGHT)
            .name ("Finger 2")
            .sourceElement (this.element)
            .reverse (true)
            .offset (10)
            .build ()
            .swipe (), null);
        performMobileGestures (asList (finger1, finger2));
        LOGGER.traceExit ();
    }
}
