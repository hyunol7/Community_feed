package org.example.Common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimerCalculator {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private TimerCalculator(){

    }


        public static LocalDateTime getCurrentDate(int daysAgo) {
            return LocalDateTime.now().minusDays(daysAgo);
        }

        public static String getFormattedDate(LocalDateTime dateTime) {
        if(dateTime == null){
            return "";
        }
        return dateTimeFormatter.format(dateTime);
        }
}
