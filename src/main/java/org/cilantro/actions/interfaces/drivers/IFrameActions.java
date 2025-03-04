

package org.cilantro.actions.interfaces.drivers;

/**
 * All frames related actions
 *
 * @author Rahul Saini
 * @since 16-Dec-2024
 */
public interface IFrameActions {
    /**
     * Switch to an iFrame.
     *
     * @param frameNameOrId Name / ID of the Iframe.
     */
    void switchTo (final String frameNameOrId);

    /**
     * Switch to an iFrame.
     *
     * @param index Index of the frame.
     */
    void switchTo (int index);

    /**
     * Switch to Parent Frame.
     */
    void switchToParent ();
}
