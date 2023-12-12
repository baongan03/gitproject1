package Utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static LocalDate parseDate(String date){
        try {
            return LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Nhập theo format 'dd-mm-yyyy'");
        }
        return null;
    }
    public static String formatDate(LocalDate localDate){
        try {
            return localDate.format(dateFormatter);
        } catch (DateTimeException e){
            e.printStackTrace();
        }
        return null;
    }
    public static LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Nhập theo format 'dd-mm-yyyy hh:mm'");
        }
        return null;
    }

    public static String formatDateTime(LocalDateTime localDateTime) {
        try {
            return localDateTime.format(dateTimeFormatter);
        } catch (DateTimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}