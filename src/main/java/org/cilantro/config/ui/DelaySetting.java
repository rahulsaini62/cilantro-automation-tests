

package org.cilantro.config.ui;

import lombok.Data;

/**
 * UI Action delay settings.
 *
 * @author Rahul Saini
 * @since 04-Dec-2024
 */
@Data
public class DelaySetting {
    private int beforeClick;
    private int beforeMouseMove;
    private int beforeSwipe = 500;
    private int beforeTap;
    private int beforeTyping;
}
