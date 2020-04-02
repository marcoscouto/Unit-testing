package com.marcoscouto.matchers;

import java.util.Calendar;
import java.util.Date;

public class CustomMatchers {

    public static DayOfWeekMatcher whatDay(Integer dayWeek){
        return new DayOfWeekMatcher(dayWeek);
    }

    public static DayOfWeekMatcher atMonday(){
        return new DayOfWeekMatcher(Calendar.MONDAY);
    }

    public static RentalDateMatcher isToday(){
        return new RentalDateMatcher(0);
    }

    public static RentalDateMatcher isTodayWithDaysDifference(Integer dayPlus){
        return new RentalDateMatcher(dayPlus);
    }

}
