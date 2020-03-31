package com.marcoscouto.services;

import com.marcoscouto.entities.Movie;
import com.marcoscouto.entities.Rental;
import com.marcoscouto.entities.User;
import com.marcoscouto.utils.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static com.marcoscouto.utils.DateUtils.addDays;

public class RentalService {

    public Rental rentMovie(User user, Movie movie) {
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setUser(user);
        rental.setInitialDate(new Date());
        rental.setPrice(movie.getPrice());

        //Entrega no dia seguinte
        Date returnDate = new Date();
        returnDate = addDays(returnDate, 1);
        rental.setFinalDate(returnDate);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        return rental;
    }

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

        Assert.assertTrue(rental.getPrice() == 14.9);
        Assert.assertTrue(DateUtils.isSameDate(rental.getInitialDate(), new Date()));
        Assert.assertTrue(DateUtils.isSameDate(rental.getFinalDate(), DateUtils.obtaingDateWithDaysDifference(1)));

    }


}
