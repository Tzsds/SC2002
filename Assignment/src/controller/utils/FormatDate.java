package controller.utils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for formatting dates.
 * This class provides methods to format LocalDate objects into strings.
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */

public class FormatDate {

    /**
     * Formats a LocalDate object into a string with the format "dd MMM yyyy".
     * 
     * @param date - The LocalDate object to be formatted.
     * @return A string representing the formatted date.
     */

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String dateString = date.format(formatter);
        return dateString;
    }
}

