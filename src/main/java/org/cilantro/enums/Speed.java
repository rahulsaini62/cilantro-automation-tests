package org.cilantro.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * UI execution speed.
 *
 * @author Rahul Saini
 * @since 27-Dec-2024
 */
@AllArgsConstructor
@Getter
public enum Speed {
    /**
     * Fast speed with no delay.
     */
    FAST (100),
    /**
     * Normal means, with delay of 200ms between each action.
     */
    NORMAL (500),
    /**
     * Slow means, with delay of 500ms between each action.
     */
    SLOW (1000);

    private final int delay;
}
