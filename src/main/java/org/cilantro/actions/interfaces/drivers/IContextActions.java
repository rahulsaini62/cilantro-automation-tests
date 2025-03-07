

package org.cilantro.actions.interfaces.drivers;

import java.util.List;

/**
 * Handles driver contexts
 *
 * @author Rahul Saini
 * @since 09-Dec-2024
 */
public interface IContextActions {
    /**
     * List all available context names.
     *
     * @return List of contexts
     */
    List<String> contexts ();

    /**
     * Gets Current context name
     *
     * @return Current context name
     */
    String currentContext ();

    /**
     * Switch back to Native app context.
     */
    void switchToNative ();

    /**
     * Switch context to the mentioned WebView context.
     *
     * @param name Name of the context
     */
    void switchToWebView (String name);

    /**
     * Switch context to the first available WebView context.
     */
    void switchToWebView ();
}
