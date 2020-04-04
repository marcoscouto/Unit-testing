package com.marcoscouto.services;

import com.marcoscouto.builders.MovieBuilder;
import com.marcoscouto.builders.RentalBuilder;
import com.marcoscouto.builders.UserBuilder;
import com.marcoscouto.dao.RentalDAO;
import com.marcoscouto.entities.Movie;
import com.marcoscouto.entities.Rental;
import com.marcoscouto.entities.User;
import com.marcoscouto.exceptions.MovieWithoutStockException;
import com.marcoscouto.exceptions.RentalException;
import com.marcoscouto.matchers.CustomMatchers;
import com.marcoscouto.utils.DateUtils;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

import java.util.*;

public class RentalServiceTest {

    @InjectMocks
    private RentalService rs;

    @Mock
    private RentalDAO dao;

    @Mock
    private SPCService spc;

    @Mock
    private EmailService email;

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        rs = PowerMockito.spy(rs);
    }

    @After
    public void tearDown() {
//        System.out.println("After");
    }

    @BeforeClass
    public static void setupClass() {
//        System.out.println("Before Class");
    }

    @AfterClass
    public static void tearDownClass() {
//        System.out.println("After Class");
    }

    @Test(expected = MovieWithoutStockException.class) // Forma Elegante
    public void shouldThrowExceptionByRentFilmWithoutStock() throws Exception {

        //Cenário
        User user = UserBuilder.oneUser().now();

        List<Movie> movies = new ArrayList<>();
        movies.addAll(Arrays.asList(
                MovieBuilder.oneMovie().noStock().now()
        ));


        //Ação

        rs.rentMovie(user, movies);
    }

    @Test // Forma Robusta
    public void shouldThrowExceptionByRentFilmWithoutStock2() {

        //Cenário
        User user = UserBuilder.oneUser().now();

        List<Movie> movies = new ArrayList<>();
        movies.addAll(Arrays.asList(
                MovieBuilder.oneMovie().noStock().now()
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
    public void shouldThrowExceptionByRentFilmWithoutStock3() throws Exception {

        //Cenário
        User user = UserBuilder.oneUser().now();

        List<Movie> movies = new ArrayList<>();
        movies.addAll(Arrays.asList(
                MovieBuilder.oneMovie().movieWithoutStock().now()
        ));


        expectedException.expect(Exception.class);
        expectedException.expectMessage("Film without stock");

        //Ação
        rs.rentMovie(user, movies);
    }

    @Test
    public void notShouldRentMovieWithoutUser() throws MovieWithoutStockException {

        //Cenário
        List<Movie> movies = new ArrayList<>();
        movies.addAll(Arrays.asList(
                MovieBuilder.oneMovie().now()
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
    public void notShouldRentMovieWithoutMovie() throws MovieWithoutStockException, RentalException {

        //Cenário
        User user = UserBuilder.oneUser().now();

        expectedException.expect(RentalException.class);
        expectedException.expectMessage("Movie not found");

        //Ação
        rs.rentMovie(user, null);
    }

    @Test
    public void shouldPay25PercentLessFor3rdMovie() throws MovieWithoutStockException, RentalException {
        //Cenário
        User user = UserBuilder.oneUser().now();
        List<Movie> movies = Arrays.asList(
                new Movie("Movie 1", 2, 4.0),
                new Movie("Movie 2", 2, 4.0),
                new Movie("Movie 3", 2, 4.0)
        );

        //Ação
        Rental rental = rs.rentMovie(user, movies);

        //Verificação
        //4+4+3=11
        Assert.assertThat(rental.getPrice().doubleValue(), CoreMatchers.is(11.0));
    }

    @Test
    public void shouldPay50PercentLessFor4thMovie() throws MovieWithoutStockException, RentalException {
        //Cenário
        User user = UserBuilder.oneUser().now();
        List<Movie> movies = Arrays.asList(
                new Movie("Movie 1", 2, 4.0),
                new Movie("Movie 2", 2, 4.0),
                new Movie("Movie 3", 2, 4.0),
                new Movie("Movie 4", 2, 4.0)
        );

        //Ação
        Rental rental = rs.rentMovie(user, movies);

        //Verificação
        //4+4+3+2=13
        Assert.assertThat(rental.getPrice().doubleValue(), CoreMatchers.is(13.0));
    }

    @Test
    public void shouldPay75PercentLessFor5thMovie() throws MovieWithoutStockException, RentalException {
        //Cenário
        User user = UserBuilder.oneUser().now();
        List<Movie> movies = Arrays.asList(
                new Movie("Movie 1", 2, 4.0),
                new Movie("Movie 2", 2, 4.0),
                new Movie("Movie 3", 2, 4.0),
                new Movie("Movie 4", 2, 4.0),
                new Movie("Movie 5", 2, 4.0)
        );

        //Ação
        Rental rental = rs.rentMovie(user, movies);

        //Verificação
        //4+4+3+2+1=14
        Assert.assertThat(rental.getPrice().doubleValue(), CoreMatchers.is(14.0));
    }

    @Test
    public void shouldPay100PercentLessFor6thMovie() throws MovieWithoutStockException, RentalException {
        //Cenário
        User user = UserBuilder.oneUser().now();
        List<Movie> movies = Arrays.asList(
                new Movie("Movie 1", 2, 4.0),
                new Movie("Movie 2", 2, 4.0),
                new Movie("Movie 3", 2, 4.0),
                new Movie("Movie 4", 2, 4.0),
                new Movie("Movie 5", 2, 4.0),
                new Movie("Movie 6", 2, 4.0)
        );

        //Ação
        Rental rental = rs.rentMovie(user, movies);

        //Verificação
        //4+4+3+2+1=14
        Assert.assertThat(rental.getPrice().doubleValue(), CoreMatchers.is(14.0));
    }

    @Test
    public void shouldNotRentMovieForUserNegative() throws Exception {
        //Cenário
        User user = UserBuilder.oneUser().now();
        List<Movie> movies = Arrays.asList(MovieBuilder.oneMovie().now());

        Mockito.when(spc.isNegative(Mockito.any(User.class))).thenReturn(true);

        //Ação
        try {
            rs.rentMovie(user, movies);
            //Verificação
            Assert.fail();
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("User negative"));
        }


        Mockito.verify(spc).isNegative(user);
    }

    @Test
    public void shouldSendEmail(){
        //Cenário
        User user = UserBuilder.oneUser().now();
        User user2 = UserBuilder.oneUser().setNome("Usuario 2").now();
        User user3 = UserBuilder.oneUser().setNome("Usuario 3").now();
        List<Rental> rentals =
                Arrays.asList(
                        RentalBuilder.oneRental().setUser(user).late().now(),
                        RentalBuilder.oneRental().setUser(user2).now(),
                        RentalBuilder.oneRental().setUser(user3).late().now(),
                        RentalBuilder.oneRental().setUser(user3).late().now()
                );

        Mockito.when(dao.findRentalPending()).thenReturn(rentals);

        //Ação
        rs.notifyDelay();

        //Verificação
        Mockito.verify(email, Mockito.times(3)).notifyDelay(Mockito.any(User.class));
        Mockito.verify(email).notifyDelay(user);
        Mockito.verify(email, Mockito.atLeast(2)).notifyDelay(user3);
        Mockito.verify(email, Mockito.never()).notifyDelay(user2);
        Mockito.verifyNoMoreInteractions(email);
    }

    @Test
    public void shouldTreatSPCError() throws Exception {
        //Cenário
        User user = UserBuilder.oneUser().now();
        List<Movie> movies = Arrays.asList(MovieBuilder.oneMovie().now());

        Mockito.when(spc.isNegative(user)).thenThrow(new Exception("Falha catastrófica"));

        //Verificação
        expectedException.expect(RentalException.class);
        expectedException.expectMessage("Problemas com SPC, tente novamente");

        //Ação
        rs.rentMovie(user, movies);
    }

    @Test
    public void shouldExtendRental(){
        //Cenário
        Rental rental = RentalBuilder.oneRental().now();

        //Ação
        rs.extendRental(rental, 3);

        //Verificação
        ArgumentCaptor<Rental> argCapt = ArgumentCaptor.forClass(Rental.class);
        Mockito.verify(dao).save(argCapt.capture());
        Rental rentalResponse = argCapt.getValue();

        Assert.assertThat(rentalResponse.getFinalDate(), CustomMatchers.isTodayWithDaysDifference(3));
    }

    @Test
    public void shouldRentMovieWithoutCalcTotal() throws Exception {
        //Cenário
        User user = UserBuilder.oneUser().now();
        List<Movie> movies = Arrays.asList(MovieBuilder.oneMovie().now());

        PowerMockito.doReturn(1.0).when(rs, "calcRentalTotal", movies);

        //Ação
        Rental rental = rs.rentMovie(user, movies);

        //Verificação
        Assert.assertThat(rental.getPrice(), CoreMatchers.is(1.0));
        PowerMockito.verifyPrivate(rs).invoke("calcRentalTotal", movies);
    }

}
