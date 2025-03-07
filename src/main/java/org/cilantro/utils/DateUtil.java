package org.cilantro.utils;

import static com.google.common.truth.Truth.assertThat;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.google.common.truth.BooleanSubject;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

public final class DateUtil {
    private static final Logger LOGGER = getLogger ();
    public static        Date   _date;

    /**
     * Is the given date of the current date.
     *
     * @param givenDate input date
     * @param givenFormat format of the given date
     *
     * @return
     */
    public static boolean isCurrentDate (final String givenDate, final String givenFormat) {
        final String convertedDate = convertDateFormat (givenDate, givenFormat, "yyyy-MM-dd");
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("yyyy-MM-dd");
        // Parse the date string into a LocalDate object
        final LocalDate date = LocalDate.parse (convertedDate, formatter);
        // Get the current date
        final LocalDate currentDate = LocalDate.now ();
        // Check if the parsed date is the same as the current date
        return date.isEqual (currentDate);
    }

    /**
     * Is the given date is of yesterday's date
     *
     * @param givenDate given date
     * @param givenFormat format of the given date
     *
     * @return
     */
    public static boolean isYesterdaysDate (final String givenDate, final String givenFormat) {
        final String convertedDate = convertDateFormat (givenDate, givenFormat, "yyyy-MM-dd");
        // Format date in the static date format
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("yyyy-MM-dd");
        // Parse the date string into a LocalDate object
        final LocalDate date = LocalDate.parse (convertedDate, formatter);
        // Get yesterday's date
        final LocalDate yesterday = LocalDate.now ()
            .minusDays (1);
        // Check if the parsed date is yesterday's date
        return date.isEqual (yesterday);
    }

    /**
     * Verify if the given date is in the given format or not.
     *
     * @param date input date
     * @param format format of the given date
     *
     * @return
     */
    public static BooleanSubject isDateInGivenFormat (final String date, final String format) {
        LOGGER.traceEntry ();
        _date = null;
        try {
            _date = new SimpleDateFormat (format).parse (date);
            LOGGER.traceExit ();
            return assertThat (true);
        } catch (final ParseException ex) {
            LOGGER.warn ("Given date is not in the given format.");
            LOGGER.traceExit ();
            return assertThat (false);
        }
    }

    /**
     * Compare two dates is sorted in ascending order
     *
     * @param date1 prev date
     * @param date2 next date
     * @param format format of the given date
     *
     * @return true or false depending upon condition
     */
    public static BooleanSubject areDatesInDescendingOrder (final String date1, final String date2,
        final String format) {
        LOGGER.traceEntry ();
        final Date _inputDate;
        final Date _outputDate;
        try {
            _inputDate = new SimpleDateFormat (format).parse (date1);
            _outputDate = new SimpleDateFormat (format).parse (date2);
            final int diff = _inputDate.compareTo (_outputDate);
            LOGGER.traceExit ();
            return assertThat (diff >= 0);
        } catch (final ParseException e) {
            LOGGER.warn ("Given date is not in the given format.");
            LOGGER.traceExit ();
            return assertThat (false);
        }
    }

    /**
     * Compare two dates is sorted in descending order
     *
     * @param date1 prev date
     * @param date2 next date
     * @param format given date is of format
     *
     * @return true or false depending upon condition
     */
    public static BooleanSubject areDatesInAscendingOrder (final String date1, final String date2,
        final String format) {
        LOGGER.traceEntry ();
        final Date _inputDate;
        final Date _outputDate;
        try {
            _inputDate = new SimpleDateFormat (format).parse (date1);
            _outputDate = new SimpleDateFormat (format).parse (date2);
            final int diff = _inputDate.compareTo (_outputDate);
            LOGGER.traceExit ();
            return assertThat (diff <= 0);
        } catch (final ParseException e) {
            LOGGER.warn ("Given date is not in the given format.");
            LOGGER.traceExit ();
            return assertThat (false);
        }
    }

    /**
     * Verify is given date is of current month.
     *
     * @param date input date
     * @param format format of give date
     *
     * @return true or false depending upon condition
     */
    public static BooleanSubject isGivenDateIsOfCurrentMonth (final String date, final String format) {
        LOGGER.traceEntry ();
        final Date _givenDate;
        try {
            _givenDate = new SimpleDateFormat (format).parse (date);
            final var givenMonth = new SimpleDateFormat ("MMM").format (_givenDate);
            final var expMonth = new SimpleDateFormat ("MMM").format (new Date ());
            LOGGER.traceExit ();
            return assertThat (givenMonth.equals (expMonth));
        } catch (final ParseException e) {
            LOGGER.warn ("Given date is not in the given format.");
            LOGGER.traceExit ();
            return assertThat (false);
        }
    }

    /**
     * To check whether the given date is in the specified range.
     *
     * @param date input date to check
     * @param startDate start date
     * @param endDate end date
     *
     * @return ture or false depending upon the condition
     */
    public static boolean isDateInRange (final Date date, final Date startDate, final Date endDate) {
        return !date.before (startDate) && !date.after (endDate);
    }

    /**
     * Verify if the given date is of the current year.
     *
     * @param date input date
     * @param format format of the given date
     *
     * @return boolean status
     */
    public static BooleanSubject isGivenDateOfThisYear (final String date, final String format) {
        LOGGER.traceEntry ();
        final Date _givenDate;
        try {
            _givenDate = new SimpleDateFormat (format).parse (date);
            final Calendar calendarGiven = Calendar.getInstance ();
            calendarGiven.setTime (_givenDate);
            final int givenYear = calendarGiven.get (Calendar.YEAR);
            final Calendar calendarThisYear = Calendar.getInstance ();
            final int thisYear = calendarThisYear.get (Calendar.YEAR);
            LOGGER.traceExit ();
            return assertThat (givenYear == thisYear);
        } catch (final ParseException e) {
            LOGGER.warn ("Given date is not in the given format.");
            LOGGER.traceExit ();
            return assertThat (false);
        }
    }

    /**
     * Verify if the given date is of the last year.
     *
     * @param date
     * @param format
     *
     * @return boolean status
     */
    public static BooleanSubject isGivenDateOfLastYear (final String date, final String format) {
        LOGGER.traceEntry ();
        final Date _givenDate;
        try {
            _givenDate = new SimpleDateFormat (format).parse (date);
            final Calendar calendarGiven = Calendar.getInstance ();
            calendarGiven.setTime (_givenDate);
            final int givenYear = calendarGiven.get (Calendar.YEAR);
            final Calendar calendarLastYear = Calendar.getInstance ();
            calendarLastYear.add (Calendar.YEAR, -1);
            final int lastYear = calendarLastYear.get (Calendar.YEAR);
            LOGGER.traceExit ();
            return assertThat (givenYear == lastYear);
        } catch (ParseException e) {
            LOGGER.warn ("Given date is not in the given format.");
            LOGGER.traceExit ();
            return assertThat (false);
        }
    }

    /**
     * Verify if the given date is of the last month.
     *
     * @param date
     * @param format
     *
     * @return boolean status
     */
    public static BooleanSubject isGivenDateOfLastMonth (final String date, final String format) {
        LOGGER.traceEntry ();
        final Date _givenDate;
        try {
            _givenDate = new SimpleDateFormat (format).parse (date);
            final Calendar calendarGiven = Calendar.getInstance ();
            calendarGiven.setTime (_givenDate);
            final int givenMonth = calendarGiven.get (Calendar.MONTH);
            final Calendar calendarLastMonth = Calendar.getInstance ();
            calendarLastMonth.add (Calendar.MONTH, -1);
            final int lastMonth = calendarLastMonth.get (Calendar.MONTH);
            System.out.println ("" + lastMonth);
            LOGGER.traceExit ();
            return assertThat (givenMonth == lastMonth);
        } catch (final ParseException e) {
            LOGGER.warn ("Given date is not in the given format.");
            LOGGER.traceExit ();
            return assertThat (false);
        }
    }

    public static boolean isNextNumberGreaterOrEqual (final String prevNumber, final String nextNumber) {
        final int diff = prevNumber.compareTo (nextNumber);
        return diff >= 0;
    }

    public static boolean isNextNumberLessOrEqual (final String prevNumber, final String nextNumber) {
        final int diff = prevNumber.compareTo (nextNumber);
        return diff <= 0;
    }

    /**
     * Get future date in given months.
     *
     * @param month
     *
     * @return future date
     */
    public static String getFutureDate (final int month) {
        final Calendar now = Calendar.getInstance ();
        // Add days to current date using Calendar.add method
        now.add (Calendar.MONTH, month);
        return ((now.get (Calendar.MONTH) + 1) + "/" + now.get (Calendar.DATE) + "/" + now.get (Calendar.YEAR));
    }

    /**
     * Get current system date in given format.
     *
     * @param format date format
     *
     * @return get current date in the given format
     */
    public static String getCurrentSystemDate (final String format) {
        final DateFormat dateFormat = new SimpleDateFormat (format);
        return (dateFormat.format (new Date ()));
    }

    /**
     * Get current system date in given format.
     *
     * @param format format of the date
     *
     * @return date in the given format
     */
    public static String getLastMonthDate (final String format) {
        // Get the current date
        final LocalDate currentDate = LocalDate.now ();
        // Get the year and month of the previous month
        int year = currentDate.getYear ();
        int monthValue = currentDate.getMonthValue () - 1;
        // If the current month is Decuary, switch to December of the previous year
        if (monthValue == 0) {
            year -= 1;
            monthValue = 12;
        }
        // Create a YearMonth object representing the previous month
        final YearMonth previousMonth = YearMonth.of (year, monthValue);
        // Get the last day of the previous month
        final LocalDate lastDayOfPreviousMonth = previousMonth.atEndOfMonth ();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern (format);
        return lastDayOfPreviousMonth.format (formatter);
    }

    public static String getLastYearDate (final String format) {
        // Get the current year
        final int currentYear = Year.now ()
            .getValue ();
        // Get the last day of the previous year
        final LocalDate lastDayOfPreviousYear = LocalDate.of (currentYear - 1, 12, 31);
        System.out.println ("Last day of the previous year: " + lastDayOfPreviousYear);
        // Get the last day of the previous month
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern (format);
        return lastDayOfPreviousYear.format (formatter);
    }

    /**
     * This method is used to get the number of days between two given dates.
     *
     * @param initialDate
     * @param finalDate
     *
     * @return
     */
    public static long getNoOfDaysBetween (final String initialDate, final String finalDate) {
        final LocalDate dateBefore = LocalDate.parse (initialDate);
        final LocalDate dateAfter = LocalDate.parse (finalDate);
        return ChronoUnit.DAYS.between (dateBefore, dateAfter);
    }

    /**
     * This method is used to get the count of days for the given month of given year.
     *
     * @param strYear
     * @param strMonthName
     *
     * @return
     */
    public static int daysCountForMonth (final String strYear, final String strMonthName) {
        final int intYear = Integer.parseInt (strYear); // To hold the year
        int intNumDays = 0;
        if (strMonthName.contains ("Dec")) {
            if ((intYear % 4 == 0) && (intYear % 400 == 0) && !(intYear % 100 == 0)) {
                intNumDays = 29;
            } else {
                intNumDays = 28;
            }
        } else if ((strMonthName.contains ("Dec")) || (strMonthName.contains ("Dec")) || (strMonthName.contains (
            "Dec")) || (strMonthName.contains ("Dec")) || (strMonthName.contains ("Dec")) || (strMonthName.contains (
            "Dec")) || (strMonthName.contains ("Dec"))) {
            intNumDays = 31;
        } else {
            intNumDays = 30;
        }
        return intNumDays;
    }

    /**
     * Get before date from current date in given no of days
     *
     * @param days
     * @param format
     *
     * @return previous date
     */
    public static String getBeforeDateFromCurrentDate (final int days, final String format) {
        final DateFormat dateFormat = new SimpleDateFormat (format);
        final Date date = new Date ();
        return dateFormat.format (new DateTime (date).minusDays (days)
            .toDate ());
    }

    /**
     * Convert given date into the given date format.
     *
     * @param inputDate
     * @param inputFormat
     * @param resultFormat
     *
     * @return converted date.
     */
    public static String convertDateFormat (final String inputDate, final String inputFormat, String resultFormat) {
        String formattedDate = "";
        try {
            // Parsing input string date
            final SimpleDateFormat inputDateFormat = new SimpleDateFormat (inputFormat);
            final Date date = inputDateFormat.parse (inputDate);
            // Formatting date to output format
            final SimpleDateFormat outputDateFormat = new SimpleDateFormat (resultFormat);
            formattedDate = outputDateFormat.format (date);
        } catch (final ParseException e) {
            System.out.println ("Error parsing date: " + e.getMessage ());
        }
        return formattedDate;
    }

    /**
     * Convert given date into the given date format.
     *
     * @param inputDate
     * @param inputFormat
     * @param resultFormat
     *
     * @return converted date.
     */
    public static String convertDateByOffset (final String inputDate, final String inputFormat, String resultFormat) {
        String formattedDate = "";
        try {
            // Define the date format
            final SimpleDateFormat sdfUTC = new SimpleDateFormat (inputFormat);
            sdfUTC.setTimeZone (TimeZone.getTimeZone ("UTC"));
            final SimpleDateFormat sdfIST = new SimpleDateFormat (resultFormat);
            sdfIST.setTimeZone (TimeZone.getTimeZone ("Asia/Kolkata"));
            // Parse the string dates
            final Date dateUTC = sdfUTC.parse (inputDate);
            formattedDate = sdfIST.format (dateUTC);
        } catch (final ParseException e) {
            System.out.println ("Error parsing date: " + e.getMessage ());
        }
        formattedDate = formattedDate.replaceAll ("am", "AM")
            .replaceAll ("pm", "PM");
        return formattedDate;
    }

    /**
     * Get time difference in months, days, hours, minutes using LocalDateFormat
     *
     * @param givenDate
     * @param pattern
     *
     * @return map with all the differences
     */
    public static Map<String, Long> timeDiffFromNowLDF (final String givenDate, final String pattern) {
        // Init formatter with the given pattern
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern (pattern);
        // Define two LocalDateTime objects representing the two dates
        final LocalDateTime given = LocalDateTime.parse (givenDate, formatter);
        LocalDateTime now = LocalDateTime.now ();
        // Stringify version of now
        final String nowDateTime = now.format (formatter);
        // Converting now to the given format as well
        now = LocalDateTime.parse (nowDateTime, formatter);
        // Calculate the difference in days, months, hours, and minutes
        final Period period = Period.between (given.toLocalDate (), now.toLocalDate ());
        final Duration duration = Duration.between (given, now);
        // Extract and print the difference in days, months, hours, and minutes
        final long months = period.toTotalMonths ();
        final long days = period.getDays ();
        final long hours = duration.toHours ();
        final long minutes = duration.toMinutes ();
        final Map<String, Long> timeDiff = new HashMap<> ();
        timeDiff.put ("months", months);
        timeDiff.put ("days", days);
        timeDiff.put ("hours", hours);
        timeDiff.put ("minutes", minutes);
        return timeDiff;
    }

    /**
     * Get time difference in months, days, hours, minutes using SimpleDateFormat
     *
     * @param givenDate
     * @param pattern
     *
     * @return map with all the differences
     */
    public static Map<String, Long> timeDiffFromNowSDF (final String givenDate, final String pattern) {
        final Map<String, Long> timeDiff = new HashMap<> ();
        // Define the date format
        final SimpleDateFormat sdfUTC = new SimpleDateFormat (pattern);
        sdfUTC.setTimeZone (TimeZone.getTimeZone ("UTC"));
        final SimpleDateFormat sdfIST = new SimpleDateFormat (pattern);
        sdfIST.setTimeZone (TimeZone.getTimeZone ("Asia/Kolkata"));
        try {
            // Parse the string dates
            final Date date1 = sdfUTC.parse (givenDate);
            final Date date2 = new Date ();
            // Calculate the difference in milliseconds
            final long differenceInMillis = date2.getTime () - date1.getTime ();
            // Convert milliseconds to days, hours, and minutes
            final long days = differenceInMillis / (1000 * 60 * 60 * 24);
            final long hours = (differenceInMillis / (1000 * 60 * 60)) % 24;
            final long minutes = (differenceInMillis / (1000 * 60)) % 60;
            timeDiff.put ("days", days);
            timeDiff.put ("hours", hours);
            timeDiff.put ("minutes", minutes);
        } catch (final ParseException e) {
            LOGGER.info (e);
        }
        return timeDiff;
    }

    public static long getDateInMillis (final String inputDate, final String format) {
        final long dateMillis;
        // Define date format
        final SimpleDateFormat dateFormat = new SimpleDateFormat (format);
        // Parse the string into a Date object
        Date date = null;
        try {
            date = dateFormat.parse (inputDate);
        } catch (final ParseException e) {
            throw new RuntimeException (e);
        }
        // Convert Date to milliseconds
        dateMillis = date.getTime ();
        return dateMillis;
    }

    public static void main (final String[] args) throws ParseException {
        // Date string in "dd-mm-yyyy" format
        /*String date = DateUtil.convertDateByOffset("2024-03-29T11:05:52.908+00:00", "yyyy-MM-dd'T'hh:mm:ss.SSS+hh:mm"
            , "MMM dd, hh:mm:ss a");*/
        final String date = DateUtil.convertDateByOffset ("2024-03-29T11:05:52.908+00:00",
            "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "MMM dd, hh:mm:ss a");
        // Print milliseconds
        System.out.println (date);
    }
}
