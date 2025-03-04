package org.cilantro.utils;

import java.util.Random;

/**
 * This class is for generating random strings, integers and mobile numbers.
 *
 * @author ranjit.biswal
 * @since
 */
public final class RandomUtil {

    public static enum Mode {
        ALPHA,
        ALPHANUMERIC,
        NUMERIC
    }

    /**
     * This method is used to generate random string of the given length and of given type.
     *
     * @param length
     * @param mode
     *
     * @return
     *
     * @throws Exception
     */
    public static String generateRandomString (final int length, final Mode mode) {
        final StringBuilder buffer = new StringBuilder ();
        final String characters = switch (mode) {
            case ALPHA -> "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            case ALPHANUMERIC -> "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            case NUMERIC -> "1234567890";
        };
        final int charactersLength = characters.length ();
        for (int i = 0; i < length; i++) {
            final double index = Math.random () * charactersLength;
            buffer.append (characters.charAt ((int) index));
        }
        return buffer.toString ();
    }

    /**
     * This method is used to generate the random mobile number
     *
     * @return
     */
    public static String generateMobileNumber () {
        final String input = Integer.toString (1234567890);
        return "(" + input.substring (0, 3) + ") " + input.substring (3, 6) + "-" + input.substring (6,
            10);
    }

    /**
     * This method is used to generate random integers.
     *
     * @return
     */
    public static int generateInteger () {
        final Random randomGenerator = new Random ();
        return randomGenerator.nextInt (95);
    }
}