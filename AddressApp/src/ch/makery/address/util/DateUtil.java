package ch.makery.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper functions that control the date
 */
public class DateUtil {

    /** The date pattern used in the conversion. You can change it as you like. */
    private static final String DATE_PATTERN = "dd.MM.yyyy";


    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * @param date the date to be returned as a string
     * @return formatted string
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * @param dateString the date as String
     * @return the date object or null if it could not be converted
     */
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     *
     * @param dateString
     * return true if the String is a valid date
     */
    public static boolean validDate(String dateString) {
        // Try to parse the String.
        return DateUtil.parse(dateString) != null;
    }
}