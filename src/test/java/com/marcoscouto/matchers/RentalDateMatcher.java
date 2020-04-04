package com.marcoscouto.matchers;

import com.marcoscouto.utils.DateUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

public class RentalDateMatcher extends TypeSafeMatcher<Date> {

    private Integer dayPlus;

    public RentalDateMatcher(Integer dayPlus){
        this.dayPlus = dayPlus;
    }

    @Override
    protected boolean matchesSafely(Date date) {
        return DateUtils.isSameDate(date, DateUtils.obtaingDateWithDaysDifference(dayPlus));
    }

    @Override
    public void describeTo(Description description) {
        Date date = DateUtils.obtaingDateWithDaysDifference(dayPlus);
        description.appendText("<"+date.toString()+">");
    }

}
