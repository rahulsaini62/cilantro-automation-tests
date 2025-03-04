package org.cilantro.enums;

/**
 * Element find wait strategy for Auto waiting for element to become intractable.
 *
 * @author Rahul Saini
 * @since 05-Dec-2024
 */
public enum WaitStrategy {
    /**
     * Wait for element to become clickable.
     */
    CLICKABLE,
    /**
     * Wait for element to become visible.
     */
    VISIBLE
}
