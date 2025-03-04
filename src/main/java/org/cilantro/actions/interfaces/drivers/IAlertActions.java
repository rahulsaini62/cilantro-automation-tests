

package org.cilantro.actions.interfaces.drivers;

import com.google.common.truth.StringSubject;

/**
 * All Alerts related actions
 *
 * @author Rahul Saini
 * @since 16-Dec-2024
 */
public interface IAlertActions {
    /**
     * Accepts browser alert.
     *
     * @return the alert message
     */
    String accept ();

    /**
     * Enters text in browser alert and accept it.
     *
     * @param text Text to enter in alert
     *
     * @return the alert message
     */
    String accept (final String text);

    /**
     * Dismisses browser alert.
     *
     * @return the alert message
     */
    String dismiss ();

    /**
     * Verify alert text, enter text in prompt and accept the alert
     *
     * @param text Text to enter in prompt
     *
     * @return {@link StringSubject}
     */
    StringSubject verifyAccept (final String text);

    /**
     * Verify alert text and accept the alert
     *
     * @return {@link StringSubject}
     */
    StringSubject verifyAccept ();

    /**
     * Verify alert text and dismissing the alert
     *
     * @return {@link StringSubject}
     */
    StringSubject verifyDismiss ();
}
