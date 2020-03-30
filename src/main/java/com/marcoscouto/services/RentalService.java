package com.marcoscouto.services;

import com.marcoscouto.entities.Movie;
import com.marcoscouto.entities.Rental;
import com.marcoscouto.entities.User;

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
        Date dataEntrega = new Date();
        dataEntrega = addDays(dataEntrega, 1);
        rental.setFinalDate(dataEntrega);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        return rental;
    }

    public static void main(String[] args) {

    }


}
