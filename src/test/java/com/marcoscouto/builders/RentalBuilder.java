package com.marcoscouto.builders;

import com.marcoscouto.entities.Rental;
import com.marcoscouto.entities.User;
import com.marcoscouto.utils.DateUtils;

import java.util.Arrays;
import java.util.Date;

public class RentalBuilder {

    private Rental rental;

    private RentalBuilder() {

    }

    public static RentalBuilder oneRental() {
        RentalBuilder rb = new RentalBuilder();
        rb.rental = new Rental();
        rb.setAttributes();
        return rb;
    }

    private void setAttributes() {
        rental.setUser(UserBuilder.oneUser().now());
        rental.setMovies(Arrays.asList(MovieBuilder.oneMovie().now()));
        rental.setPrice(4.0);
        rental.setInitialDate(new Date());
        rental.setFinalDate(DateUtils.addDays(new Date(), 1));
    }

    public RentalBuilder setFinalDate(Date date){
        rental.setFinalDate(date);
        return this;
    }

    public RentalBuilder setUser(User user){
        rental.setUser(user);
        return this;
    }

    public RentalBuilder late(){
        rental.setInitialDate(DateUtils.obtaingDateWithDaysDifference(-4));
        rental.setFinalDate(DateUtils.obtaingDateWithDaysDifference(-2));
        return this;
    }

    public Rental now(){
        return rental;
    }
}
