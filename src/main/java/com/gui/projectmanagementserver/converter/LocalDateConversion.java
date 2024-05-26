package com.gui.projectmanagementserver.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConversion {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static String localDateToString(LocalDate date) {
        return date.format(formatter) ;
    }

    public static LocalDate stringToLocalDate(String date_str){
        return LocalDate.parse(date_str, formatter) ;
    }
}
