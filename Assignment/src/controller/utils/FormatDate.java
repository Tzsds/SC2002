package controller.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatDate {
    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String dateString = date.format(formatter);
        return dateString;
    }
}

