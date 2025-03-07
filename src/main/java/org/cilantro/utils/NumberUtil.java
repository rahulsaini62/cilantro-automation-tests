package org.cilantro.utils;

import static com.google.common.truth.Truth.assertThat;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.text.DecimalFormat;
import java.util.Random;

import com.google.common.truth.BooleanSubject;
import com.google.common.truth.DoubleSubject;
import com.google.common.truth.IntegerSubject;
import org.apache.logging.log4j.Logger;

public final class NumberUtil {
    private static final Logger LOGGER = getLogger ();

    /**
     * Generate random number for given bound
     * @param bound
     * @return number
     */
    public static Number generateRandomNumber (final int bound) {
        LOGGER.traceEntry ();
        final Random random = new Random ();
        // Generates a random digit between 0 and 9
        return LOGGER.traceExit (random.nextInt (bound));
    }

    /**
     * Verify the given double number.
     * @param input
     * @return double subject
     */
    public static DoubleSubject verifyDouble (final Double input) {
        LOGGER.traceEntry ();
        LOGGER.traceExit ("Verifying input double: {}", input);
        return assertThat (input);
    }

    /**
     * Round off the given double number up to two decimal places.
     * @param input
     * @return round off number
     */
    public static Double roundOff (final Double input) {
        LOGGER.traceEntry ();
        LOGGER.info ("Rounding off the given double: {}", input);
        final Double output = Math.round (input * 100) / 100.00;
        LOGGER.traceExit ("Rounding off to: {}", output);
        return output;
    }

    /**
     * Round off the given double number up to two decimal places.
     * @param number
     * @return round off number
     */
    public static String formatDecimalToTwoPlaces (final Double number) {
        LOGGER.traceEntry ();
        LOGGER.info ("Rounding off the given double: {}", number);
        final DecimalFormat decimalFormat = new DecimalFormat("0.00");
        // Format the number using DecimalFormat
        final String formattedNumber = decimalFormat.format(number);
        LOGGER.traceExit ("Rounding off to: {}", formattedNumber);
        return formattedNumber;
    }

    /**
     * Verify the given integer.
     * @param input
     * @return integer subject
     */
    public static IntegerSubject verifyInteger (final int input) {
        LOGGER.traceEntry ();
        LOGGER.traceExit ("Verifying input integer: {}", input);
        return assertThat (input);
    }

    /**
     * Verify the given numbers are sorted in descending order.
     * @param arg1
     * @param arg2
     * @return boolean subject
     */
    public static BooleanSubject areNumbersInDescendingOrder (final double arg1, final double arg2) {
        LOGGER.traceEntry ();
        LOGGER.traceExit ("Verifying input double: {} is greater than the input double: {}", arg1);
        return (arg1 >= arg2) ? assertThat (true) : assertThat (false);
    }

    /**
     * Verify the given numbers are sorted in ascending order.
     * @param arg1
     * @param arg2
     * @return boolean subject
     */
    public static BooleanSubject areNumbersInAscendingOrder (final double arg1, final double arg2) {
        LOGGER.traceEntry ();
        LOGGER.traceExit ("Verifying input double: {} is smaller than the input double: {}", arg1);
        return (arg1 <= arg2) ? assertThat (true) : assertThat (false);
    }

    /**
     * Verify the given numbers are sorted in descending order.
     * @param arg1
     * @param arg2
     * @return boolean subject
     */
    public static BooleanSubject areNumbersInDescendingOrder (final int arg1, final int arg2) {
        LOGGER.traceEntry ();
        LOGGER.traceExit ("Verifying input integer: {}", arg1);
        return (arg1 >= arg2) ? assertThat (true) : assertThat (false);
    }

    /**
     * Verify the given numbers are sorted in ascending order.
     * @param arg1
     * @param arg2
     * @return boolean subject
     */
    public static BooleanSubject areNumbersInAscendingOrder (final int arg1, final int arg2) {
        LOGGER.traceEntry ();
        LOGGER.traceExit ("Verifying input integer: {}", arg1);
        return (arg1 <= arg2) ? assertThat (true) : assertThat (false);
    }
}
