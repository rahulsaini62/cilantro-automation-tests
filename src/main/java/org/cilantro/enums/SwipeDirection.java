package org.cilantro.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Swipe direction
 *
 * @author Rahul Saini
 * @since 26-Dec-2024
 */
@Getter
@AllArgsConstructor
public enum SwipeDirection {
    /**
     * Down direction: Finger moving from bottom to top
     */
    DOWN (0, 1),
    /**
     * Left direction: Finger moving from left to right
     */
    LEFT (-1, 0),
    /**
     * Right direction: Finger moving from right to left
     */
    RIGHT (1, 0),
    /**
     * Up direction: Finger moving from top to bottom
     */
    UP (0, -1);

    private final int x;
    private final int y;
}
