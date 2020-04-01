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
import java.util.List;

import static com.marcoscouto.utils.DateUtils.addDays;

public class RentalService {

    public Rental rentMovie(User user, List<Movie> movies) throws MovieWithoutStockException, RentalException {

        if (user == null) throw new RentalException("User not found");

        if (movies == null || movies.isEmpty()) throw new RentalException("Movie not found");

        for (Movie movie : movies) {
            if (movie.getStock() == 0)
                throw new MovieWithoutStockException("Film without stock");
        }

        Rental rental = new Rental();
        rental.setMovies(movies);
        rental.setUser(user);
        rental.setInitialDate(new Date());
        rental.setPrice(movies.stream().mapToDouble(x -> x.getPrice()).sum());

        //Entrega no dia seguinte
        Date returnDate = new Date();
        returnDate = addDays(returnDate, 1);
        rental.setFinalDate(returnDate);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        return rental;
    }

}
