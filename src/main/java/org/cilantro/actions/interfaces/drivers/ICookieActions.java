

package org.cilantro.actions.interfaces.drivers;

import java.util.List;

import org.openqa.selenium.Cookie;

/**
 * All cookies related actions
 *
 * @author Rahul Saini
 * @since 16-Dec-2024
 */
public interface ICookieActions {
    /**
     * Gets a particular cookie from browser.
     *
     * @param name Cookie name
     *
     * @return {@link Cookie}
     */
    Cookie cookie (final String name);

    /**
     * Gets all the browser cookies.
     *
     * @return List of cookie names.
     */
    List<String> cookies ();

    /**
     * Deletes browser cookie.
     *
     * @param name cookie name
     */
    void delete (final String name);

    /**
     * Deletes all browser cookies.
     */
    void deleteAll ();
}
