package com.marcoscouto.services;

import com.marcoscouto.entities.Movie;
import com.marcoscouto.entities.Rental;
import com.marcoscouto.entities.User;
import com.marcoscouto.exceptions.MovieWithoutStockException;
import com.marcoscouto.exceptions.RentalException;
import com.marcoscouto.utils.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static com.marcoscouto.utils.DateUtils.addDays;

public class RentalService {

    public Rental rentMovie(User user, Movie movie) throws MovieWithoutStockException, RentalException {

        if (user == null) throw new RentalException("User not found");

        if (movie == null) throw new RentalException("Movie not found");

        if (movie.getStock() == 0) throw new MovieWithoutStockException("Film without stock");

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

}
