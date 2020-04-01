package com.marcoscouto.services;

import com.marcoscouto.entities.Movie;
import com.marcoscouto.entities.Rental;
import com.marcoscouto.entities.User;
import com.marcoscouto.exceptions.MovieWithoutStockException;
import com.marcoscouto.exceptions.RentalException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CalcRentalValueTest {

    private RentalService rs;

    @Parameterized.Parameter
    public List<Movie> movies;

    @Parameterized.Parameter(value = 1)
    public Double rentalValue;

    @Parameterized.Parameter(value = 2)
    public String scenario;

    @Before
    public void setup() {
        rs = new RentalService();
    }

    private static Movie movie1 = new Movie("Movie 1", 2, 4.0);
    private static Movie movie2 = new Movie("Movie 2", 2, 4.0);
    private static Movie movie3 = new Movie("Movie 3", 2, 4.0);
    private static Movie movie4 = new Movie("Movie 4", 2, 4.0);
    private static Movie movie5 = new Movie("Movie 5", 2, 4.0);
    private static Movie movie6 = new Movie("Movie 6", 2, 4.0);
    private static Movie movie7 = new Movie("Movie 7", 2, 4.0);

    @Parameterized.Parameters(name = "{2}")
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList(movie1, movie2), 8.0, "2 movies - not discount"},
                {Arrays.asList(movie1, movie2, movie3), 11.0, "3 movies - 25%"},
                {Arrays.asList(movie1, movie2, movie3, movie4), 13.0, "4 movies - 50%"},
                {Arrays.asList(movie1, movie2, movie3, movie4, movie5), 14.0, "5 movies - 75%"},
                {Arrays.asList(movie1, movie2, movie3, movie4, movie5, movie6), 14.0, "6 movies - 100%"},
                {Arrays.asList(movie1, movie2, movie3, movie4, movie5, movie6, movie7), 18.0, "7 movies - not discount"}
        });
    }

    @Test
    public void shouldCalcRentalValueByDescounts() throws MovieWithoutStockException, RentalException {
        //Cenário
        User user = new User("Marcos");

        //Ação
        Rental rental = rs.rentMovie(user, movies);

        //Verificação
        //4+4+3+2+1=14
        Assert.assertThat(rental.getPrice().doubleValue(), CoreMatchers.is(rentalValue));
    }

}
