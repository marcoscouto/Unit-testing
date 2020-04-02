package com.marcoscouto.matchers;

import java.util.Calendar;

public class CustomMatchers {

    public static DayOfWeekMatcher whatDay(Integer dayWeek){
        return new DayOfWeekMatcher(dayWeek);
    }

    public static DayOfWeekMatcher atMonday(){
        return new DayOfWeekMatcher(Calendar.MONDAY);
    }

}
