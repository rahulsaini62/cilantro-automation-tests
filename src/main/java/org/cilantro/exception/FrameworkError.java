package org.cilantro.exception;

/**
 * Common Framework specific Error.
 *
 * @author Rahul Saini
 * @since 17-Dec-2024
 */
public class FrameworkError extends Error {
    /**
     * Framework Error with only message.
     *
     * @param message Error message
     */
    public FrameworkError (final String message) {
        super (message);
    }

    /**
     * Framework Error with message and cause.
     *
     * @param message Error message
     * @param cause Error cause
     */
    public FrameworkError (final String message, final Throwable cause) {
        super (message, cause);
    }
}
