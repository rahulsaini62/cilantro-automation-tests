package org.cilantro.actions.drivers;

import static org.cilantro.actions.CommonActions.getDriverAttribute;
import static org.cilantro.actions.CommonActions.performDriverAction;
import static org.cilantro.enums.ListenerType.COOKIE_ACTION;
import static org.cilantro.manager.ParallelSession.getSession;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.List;

import org.cilantro.actions.interfaces.drivers.ICookieActions;
import org.cilantro.actions.interfaces.listeners.drivers.ICookieActionsListener;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;

/**
 * Class with all methods to handle cookies
 *
 * @author Rahul Saini
 * @since 16-Dec-2024
 */
public class CookieActions implements ICookieActions {
    private static final ICookieActions COOKIE_ACTIONS = new CookieActions ();
    private static final Logger         LOGGER         = getLogger ();

    /**
     * Handles all cookies related actions
     *
     * @return {@link ICookieActions} instance object
     */
    public static ICookieActions withCookies () {
        return COOKIE_ACTIONS;
    }

    private final ICookieActionsListener listener;

    private CookieActions () {
        this.listener = getSession ().getListener (COOKIE_ACTION);
    }

    @Override
    public Cookie cookie (final String name) {
        LOGGER.traceEntry ();
        LOGGER.info ("Get Cookie named [{}]...", name);
        ofNullable (this.listener).ifPresent (l -> l.onCookie (name));
        return getDriverAttribute (driver -> driver.manage ()
            .getCookieNamed (name), null);
    }

    @Override
    public List<String> cookies () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting all the cookies...");
        ofNullable (this.listener).ifPresent (ICookieActionsListener::onCookies);
        return getDriverAttribute (driver -> driver.manage ()
            .getCookies ()
            .stream ()
            .map (Cookie::getName)
            .toList (), emptyList ());
    }

    @Override
    public void delete (final String name) {
        LOGGER.traceEntry ();
        LOGGER.info ("Delete Cookie named [{}]...", name);
        ofNullable (this.listener).ifPresent (l -> l.onDelete (name));
        performDriverAction (driver -> driver.manage ()
            .deleteCookieNamed (name));
        LOGGER.traceExit ();
    }

    @Override
    public void deleteAll () {
        LOGGER.traceEntry ();
        LOGGER.info ("Deleting all the cookies...");
        ofNullable (this.listener).ifPresent (ICookieActionsListener::onDeleteAll);
        performDriverAction (driver -> driver.manage ()
            .deleteAllCookies ());
        LOGGER.traceExit ();
    }
}
