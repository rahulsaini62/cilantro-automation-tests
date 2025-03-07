

package org.cilantro.actions.interfaces.drivers;

import com.google.common.truth.StringSubject;

/**
 * All navigation related actions
 *
 * @author Rahul Saini
 * @since 16-Dec-2024
 */
public interface INavigateActions {
    /**
     * Navigate back to previous page on browser.
     */
    void back ();

    /**
     * Navigate forward to next page on browser.
     */
    void forward ();

    /**
     * Get current url of the browser.
     *
     * @return current url of the browser
     */
    String getUrl ();

    /**
     * Refreshes browser page.
     */
    void refresh ();

    /**
     * Navigate to url on browser.
     *
     * @param url url to navigate to
     */
    void to (final String url);

    /**
     * Navigate to the base URL.
     */
    void toBaseUrl ();

    /**
     * Verify browser url.
     *
     * @return {@link StringSubject} to verify browser url
     */
    StringSubject verifyUrl ();
}
