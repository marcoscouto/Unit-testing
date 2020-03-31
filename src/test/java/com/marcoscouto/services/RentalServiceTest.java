package com.marcoscouto.services;

import com.marcoscouto.entities.Movie;
import com.marcoscouto.entities.Rental;
import com.marcoscouto.entities.User;
import com.marcoscouto.utils.DateUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.Date;

public class RentalServiceTest {

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Test
    public void test() {

        //Cenário
        RentalService rs = new RentalService();

        User u = new User();
        u.setName("Marcos");

        Movie m = new Movie();
        m.setName("A volta dos que não foram");
        m.setStock(2);
        m.setPrice(14.90);

        //Ação

        Rental rental = rs.rentMovie(u, m);

        //Verificação

        errorCollector.checkThat( rental.getPrice().doubleValue(), CoreMatchers.is(14.9));
        errorCollector.checkThat(DateUtils.isSameDate(rental.getInitialDate(), new Date()), CoreMatchers.is(true));
        errorCollector.checkThat(DateUtils.isSameDate(rental.getFinalDate(), DateUtils.obtaingDateWithDaysDifference(1)), CoreMatchers.is(true));

    }
}
