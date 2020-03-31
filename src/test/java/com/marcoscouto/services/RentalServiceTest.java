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
import org.junit.rules.ExpectedException;

import java.util.Date;

public class RentalServiceTest {

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void test() throws Exception {

        //Cenário
        RentalService rs = new RentalService();

        User u = new User();
        u.setName("Marcos");

        Movie m = new Movie();
        m.setName("A volta dos que não foram");
        m.setStock(1);
        m.setPrice(14.90);

        //Ação

        Rental rental = rs.rentMovie(u, m);

        //Verificação

        errorCollector.checkThat( rental.getPrice().doubleValue(), CoreMatchers.is(14.9));
        errorCollector.checkThat(DateUtils.isSameDate(rental.getInitialDate(), new Date()), CoreMatchers.is(true));
        errorCollector.checkThat(DateUtils.isSameDate(rental.getFinalDate(), DateUtils.obtaingDateWithDaysDifference(1)), CoreMatchers.is(true));

    }

    @Test(expected = Exception.class)
    public void rentalTest_movieWithoutStock() throws Exception {

        //Cenário
        RentalService rs = new RentalService();

        User u = new User();
        u.setName("Marcos");

        Movie m = new Movie();
        m.setName("A volta dos que não foram");
        m.setStock(0);
        m.setPrice(14.90);

        //Ação

        rs.rentMovie(u, m);

    }

    @Test
    public void rentalTest_movieWithoutStock2() {

        //Cenário
        RentalService rs = new RentalService();

        User u = new User();
        u.setName("Marcos");

        Movie m = new Movie();
        m.setName("A volta dos que não foram");
        m.setStock(0);
        m.setPrice(14.90);

        //Ação

        try {
            rs.rentMovie(u, m);
            Assert.fail("Should be generate an exception");
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Film without stock"));
        }

    }

    @Test
    public void rentalTest_movieWithoutStock3() throws Exception {

        //Cenário
        RentalService rs = new RentalService();

        User u = new User();
        u.setName("Marcos");

        Movie m = new Movie();
        m.setName("A volta dos que não foram");
        m.setStock(0);
        m.setPrice(14.90);

        expectedException.expect(Exception.class);
        expectedException.expectMessage("Film without stock");

        //Ação
        rs.rentMovie(u, m);


    }
}
