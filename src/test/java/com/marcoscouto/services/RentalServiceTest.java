package com.marcoscouto.services;

import com.marcoscouto.entities.Movie;
import com.marcoscouto.entities.Rental;
import com.marcoscouto.entities.User;
import com.marcoscouto.exceptions.MovieWithoutStockException;
import com.marcoscouto.exceptions.RentalException;
import com.marcoscouto.utils.DateUtils;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RentalServiceTest {

    private RentalService rs;

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup(){
        rs = new RentalService();
    }

    @After
    public void tearDown(){
//        System.out.println("After");
    }

    @BeforeClass
    public static void setupClass(){
//        System.out.println("Before Class");
    }

    @AfterClass
    public static void tearDownClass(){
//        System.out.println("After Class");
    }

    @Test
    public void test() throws Exception {

        //Cenário
        User user = new User("Marcos");

        List<Movie> movies = new ArrayList<>();
        movies.addAll(Arrays.asList(
                new Movie("A volta dos que não foram", 1, 14.9),
                new Movie("As tranças do rei careca", 2, 10.7)
        ));

        //Ação

        Rental rental = rs.rentMovie(user, movies);

        //Verificação

        errorCollector.checkThat(rental.getPrice().doubleValue(), CoreMatchers.is(25.6));
        errorCollector.checkThat(DateUtils.isSameDate(rental.getInitialDate(), new Date()), CoreMatchers.is(true));
        errorCollector.checkThat(DateUtils.isSameDate(rental.getFinalDate(), DateUtils.obtaingDateWithDaysDifference(1)), CoreMatchers.is(true));

    }

    @Test(expected = MovieWithoutStockException.class) // Forma Elegante
    public void rentalTest_movieWithoutStock() throws Exception {

        //Cenário
        User user = new User("Marcos");

        List<Movie> movies = new ArrayList<>();
        movies.addAll(Arrays.asList(
                new Movie("A volta dos que não foram", 0, 14.9),
                new Movie("As tranças do rei careca", 0, 10.7)
        ));


        //Ação

        rs.rentMovie(user, movies);
    }

    @Test // Forma Robusta
    public void rentalTest_movieWithoutStock2() {

        //Cenário
        User user = new User("Marcos");

        List<Movie> movies = new ArrayList<>();
        movies.addAll(Arrays.asList(
                new Movie("A volta dos que não foram", 0, 14.9),
                new Movie("As tranças do rei careca", 0, 10.7)
        ));


        //Ação

        try {
            rs.rentMovie(user, movies);
            Assert.fail("Should be generate an exception");
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Film without stock"));
        }
    }

    @Test // Forma Nova
    public void rentalTest_movieWithoutStock3() throws Exception {

        //Cenário
        User user = new User("Marcos");

        List<Movie> movies = new ArrayList<>();
        movies.addAll(Arrays.asList(
                new Movie("A volta dos que não foram", 0, 14.9),
                new Movie("As tranças do rei careca", 0, 10.7)
        ));


        expectedException.expect(Exception.class);
        expectedException.expectMessage("Film without stock");

        //Ação
        rs.rentMovie(user, movies);
    }

    @Test
    public void rentalTest_userNotFound() throws MovieWithoutStockException {

        //Cenário
        List<Movie> movies = new ArrayList<>();
        movies.addAll(Arrays.asList(
                new Movie("A volta dos que não foram", 1, 14.9),
                new Movie("As tranças do rei careca", 2, 10.7)
        ));


        //Ação
        try {
            rs.rentMovie(null, movies);
            Assert.fail("User founded");
        } catch (RentalException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("User not found"));
        }
    }

    @Test
    public void rentalTest_movieNotFound() throws MovieWithoutStockException, RentalException {

        //Cenário
        User user = new User("Marcos");

        expectedException.expect(RentalException.class);
        expectedException.expectMessage("Movie not found");

        //Ação
        rs.rentMovie(user, null);
    }
}
