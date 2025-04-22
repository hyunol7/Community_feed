package org.example.Common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimerCalculator {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private TimerCalculator(){

    }


        public static LocalDate getCurrentDate(int daysAgo) {
        LocalDate currDate = LocalDate.now();
        return currDate.minusDays(daysAgo);
        }

        public static String getFormattedDate(LocalDateTime dateTime) {
        if(dateTime == null){
            return "";
        }
        return dateTimeFormatter.format(dateTime);
        }
}
