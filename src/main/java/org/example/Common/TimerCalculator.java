package org.example.Common;

import java.time.LocalDate;

public class TimerCalculator {

    private TimerCalculator(){

    }


        public static LocalDate getCurrentDate(int daysAgo) {
        LocalDate currDate = LocalDate.now();
        return currDate.minusDays(daysAgo);
        }
}
