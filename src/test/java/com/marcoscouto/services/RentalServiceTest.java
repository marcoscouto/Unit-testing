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

import java.util.Date;

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

    @Test(expected = MovieWithoutStockException.class) // Forma Elegante
    public void rentalTest_movieWithoutStock() throws Exception {

        //Cenário
        User u = new User();
        u.setName("Marcos");

        Movie m = new Movie();
        m.setName("A volta dos que não foram");
        m.setStock(0);
        m.setPrice(14.90);

        //Ação

        rs.rentMovie(u, m);
    }

    @Test // Forma Robusta
    public void rentalTest_movieWithoutStock2() {

        //Cenário
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

    @Test // Forma Nova
    public void rentalTest_movieWithoutStock3() throws Exception {

        //Cenário
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

    @Test
    public void rentalTest_userNotFound() throws MovieWithoutStockException {

        //Cenário
        Movie m = new Movie();
        m.setName("A volta dos que não foram");
        m.setStock(1);
        m.setPrice(14.90);

//        User u = new User();
//        u.setName("Marcos");

        //Ação
        try {
            rs.rentMovie(null, m);
            Assert.fail("User founded");
        } catch (RentalException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("User not found"));
        }
    }

    @Test
    public void rentalTest_movieNotFound() throws MovieWithoutStockException, RentalException {

        //Cenário
//        Movie m = new Movie();
//        m.setName("A volta dos que não foram");
//        m.setStock(1);
//        m.setPrice(14.90);

        User u = new User();
        u.setName("Marcos");

        expectedException.expect(RentalException.class);
        expectedException.expectMessage("Movie not found");

        //Ação
        rs.rentMovie(u, null);
    }
}
