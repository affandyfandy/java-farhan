package aliramadhan.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    /**
     * Parses a date string in the format "d/M/yyyy" and returns a LocalDate object.
     */
    public static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * Formats the given LocalDate object into a string in the format "d/M/yyyy".
     */
    public static String formatDate(LocalDate date) {
        return date.format(formatter);
    }
}