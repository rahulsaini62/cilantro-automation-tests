package org.cilantro.utils;

import static com.google.common.truth.Truth.assertThat;
import static org.apache.commons.text.StringSubstitutor.createInterpolator;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.truth.BooleanSubject;
import com.google.common.truth.StringSubject;
import org.apache.commons.text.StringSubstitutor;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.openqa.selenium.InvalidArgumentException;

/**
 * String utils.
 *
 * @author Rahul Saini
 * @since 24-Dec-2024
 */
public final class StringUtils {
    private static final Logger LOGGER             = getLogger ();
    private static final String SURROUNDING_QUOTES = "^[\"'].*[\"']$";

    /**
     * Returns a formatted String by replacing each instance of {} placeholders  with the given arguments.
     *
     * @param messagePattern the message pattern containing placeholders.
     * @param arguments the arguments to be used to replace placeholders.
     *
     * @return String the formatted message
     */
    public static String format (final String messagePattern, Object... arguments) {
        return ParameterizedMessage.format (messagePattern, arguments);
    }

    /**
     * Returns the string passed without surrounding single (') or double (") quotes if they exist. Otherwise it returns
     * the string unchanged. Quotes inside the string will not be removed. Likewise, if there are not matching quotes on
     * both ends, a leading or trailing quote will be left unmodified.
     *
     * @param text String the text to check for surrounding quotes
     *
     * @return String the text without surrounding quotes (if they exist)
     */
    public static String stripSurroundingQuotes (final String text) {
        if (text == null)
            return null;
        final Pattern pattern = Pattern.compile (SURROUNDING_QUOTES);
        if (pattern.matcher (text)
            .find ())
            return text.substring (1, text.length () - 1);
        return text;
    }

    /**
     * Constructs the ordinal string of the given int. For example "1st", "2nd", "111th"
     *
     * @param i int the integer to create an ordinal string for
     *
     * @return String the ordinal string
     */
    public static String ordinal (int i) {
        if (i < 0)
            throw new InvalidArgumentException ("Integer must be non-negative to produce ordinal.");
        final String[] suffixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (i % 100) {
            case 11, 12, 13 -> {
                return i + "th";
            }
            default -> {
                return i + suffixes[i % 10];
            }
        }
    }

    /**
     * Replace all variables in the given string with the values from system environment variables, system properties,
     * etc.
     *
     * @param value String to be interpolated
     *
     * @return Interpolated string
     */
    public static String interpolate (final String value) {
        LOGGER.traceEntry ("Interpolating string: {}", value);
        var result = value;
        if (value != null && value.startsWith ("${")) {
            final var substitute = createInterpolator ();
            substitute.setEnableSubstitutionInVariables (true);
            result = substitute.replace (value);
        }
        LOGGER.traceExit ();
        return result;
    }

    /**
     * Replace all the variables in the given string with the values from the specified map.
     *
     * @param value String to be interpolated
     * @param valuesMap Map of values to lookup for variables
     *
     * @return Interpolated string
     */
    public static String interpolate (final String value, final Map<String, String> valuesMap) {
        LOGGER.traceEntry ("Interpolating string: {}", value);
        var result = value;
        if (value.contains ("${")) {
            final var substitute = new StringSubstitutor (valuesMap);
            substitute.setEnableSubstitutionInVariables (true);
            result = substitute.replace (value);
        }
        LOGGER.traceExit ();
        return result;
    }

    public static StringSubject verifyString (final String input) {
        LOGGER.traceEntry ("Verifying input string: {}", input);
        LOGGER.traceExit ();
        return assertThat (input);
    }

    public static BooleanSubject isAlphanumeric (final String input) {
        LOGGER.traceEntry ("Checking string for alphanumeric: {}", input);
        final var REGEX = "^[a-zA-Z0-9]*$";
        final Pattern PATTERN = Pattern.compile (REGEX);
        final Matcher MATCHER = PATTERN.matcher (input);
        LOGGER.traceExit ();
        return assertThat (MATCHER.matches ());
    }

    public static BooleanSubject isAlphanumericWithSpace (final String input) {
        LOGGER.traceEntry ("Checking string for alphanumeric: {}", input);
        final var REGEX = "^[a-zA-Z0-9\\s+]*$";
        final Pattern PATTERN = Pattern.compile (REGEX);
        final Matcher MATCHER = PATTERN.matcher (input);
        LOGGER.traceExit ();
        return assertThat (MATCHER.matches ());
    }

    public static BooleanSubject isAlphanumericWithHyphen (final String input) {
        LOGGER.traceEntry ("Checking string for alphanumeric: {}", input);
        final var REGEX = "^[a-zA-Z0-9-]*$";
        final Pattern PATTERN = Pattern.compile (REGEX);
        final Matcher MATCHER = PATTERN.matcher (input);
        LOGGER.traceExit ();
        return assertThat (MATCHER.matches ());
    }

    public static BooleanSubject isStateValid (final String state) {
        LOGGER.traceEntry ();
        final var REGEX = "AL|AK|AR|AZ|CA|CO|CT|DE|DC|FL|GA|HI|ID|IL|IN|IA|KS|KY|LA|ME|MD|MA|MI|MN" + "|MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VT|VA|WA|WV|WI|WY";
        final Pattern pattern = Pattern.compile (REGEX);
        final Matcher matcher = pattern.matcher (state);
        LOGGER.traceExit ();
        return assertThat (matcher.matches ());
    }

    public static BooleanSubject isValidDouble (final String input) {
        LOGGER.traceEntry ();
        var isValidDouble = false;
        try {
            Double.parseDouble (input);
            isValidDouble = true;
        } catch (final Exception e) {
            LOGGER.warn (e);
        }
        LOGGER.traceExit ();
        return assertThat (isValidDouble);
    }

    public static BooleanSubject isValidEmail (final String email) {
        LOGGER.traceEntry ();
        final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        final Pattern pattern = Pattern.compile (EMAIL_REGEX);
        final Matcher matcher = pattern.matcher (email);
        LOGGER.traceExit ();
        return assertThat (matcher.matches ());
    }

    public static BooleanSubject isValidPhoneNumber (final String phoneNumber) {
        final String US_PATTERN = "^\\+?\\d{1,3}( )?-?\\d{3}-?\\d{4}$";
        final String IN_PATTERN = "^(\\+\\d{1,3}( )?)?(0/91)?[7-9][0-9]{9}$";
        LOGGER.traceEntry ();
        final Pattern usPattern = Pattern.compile (US_PATTERN);
        final Pattern inPattern = Pattern.compile (IN_PATTERN);
        final Matcher usMatcher = usPattern.matcher (phoneNumber);
        final Matcher inMatcher = inPattern.matcher (phoneNumber);
        LOGGER.traceExit ();
        return assertThat (usMatcher.matches () || inMatcher.matches ());
    }

    public static BooleanSubject isSortedInAscendingOrder (final List<String> list) {
        final int size = list.size ();
        for (int i = 0; i < size - 1; i++) {
            final String current = list.get (i);
            final String next = list.get (i + 1);
            if (current.compareTo (next) > 0) {
                // List is not sorted
                return assertThat (false);
            }
        }
        // List is sorted
        return assertThat (true);
    }

    public static BooleanSubject isSortedInDescendingOrder (final List<String> list) {
        final int size = list.size ();
        for (int i = 0; i < size - 1; i++) {
            final String current = list.get (i);
            final String next = list.get (i + 1);
            if (current.compareTo (next) < 0) {
                // List is not reverse sorted
                return assertThat (false);
            }
        }
        // List is reverse sorted
        return assertThat (true);
    }

    public static BooleanSubject verifyListOfString (final List<String> list, final String input) {
        LOGGER.traceEntry ();
        LOGGER.info ("Checking the list: {} contains the given string: {}", list, input);
        // List contains given string
        LOGGER.traceExit ();
        return assertThat (list.contains (input));
    }

    public static BooleanSubject isStringsInAscendingOrder (final String input1, final String input2) {
        LOGGER.traceEntry ();
        LOGGER.info ("Comparing the input1: {} with the input2: {}", input1, input2);
        // List contains given string
        LOGGER.traceExit ();
        if (input1.compareTo (input2) < 0)
            return assertThat (true);
        else if (input1.compareTo (input2) == 0)
            return assertThat (true);
        else
            return assertThat (false);
    }

    public static BooleanSubject isStringsInDescendingOrder (final String input1, final String input2) {
        LOGGER.traceEntry ();
        LOGGER.info ("Comparing the input1: {} with the input2: {}", input1, input2);
        // List contains given string
        LOGGER.traceExit ();
        if (input1.compareTo (input2) > 0)
            return assertThat (true);
        else if (input1.compareTo (input2) == 0)
            return assertThat (true);
        else
            return assertThat (false);
    }

    public static BooleanSubject isSorted (final String str1, final String str2) {
        if (str1.length () != str2.length ()) {
            return assertThat (false); // Strings with different lengths cannot be sorted the same
        }
        for (int i = 0; i < str1.length (); i++) {
            final char char1 = str1.charAt (i);
            final char char2 = str2.charAt (i);
            // Compare characters using their Unicode code points
            if (char1 > char2) {
                return assertThat (false); // str1 is not lexicographically smaller than str2
            }
        }
        return assertThat (true); // Strings are alphabetically sorted the same
    }

    public static String toCamelCase (final String input) {
        if (input == null || input.isEmpty ()) {
            return input;
        }
        final StringBuilder camelCase = new StringBuilder ();
        // Split the input string into words
        final String[] words = input.trim ()
            .split ("\\s+");
        // Capitalize the first letter of each word except the first word
        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            if (i == 0) {
                camelCase.append (word.toLowerCase ());
            } else {
                camelCase.append (Character.toUpperCase (word.charAt (0)))
                    .append (word.substring (1)
                        .toLowerCase ());
            }
        }
        return camelCase.toString ();
    }

    private StringUtils () {
        // Utility class
    }
}
