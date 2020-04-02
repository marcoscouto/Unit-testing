package com.marcoscouto.matchers;

import com.marcoscouto.utils.DateUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DayOfWeekMatcher extends TypeSafeMatcher<Date> {

    private Integer dayOfWeek;

    public DayOfWeekMatcher(Integer dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    protected boolean matchesSafely(Date date) {
        return DateUtils.verifyDayOfWeek(date, dayOfWeek);
    }

    @Override
    public void describeTo(Description description) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        String dateName = date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pt", "BR"));
        description.appendText(dateName);
    }
}
