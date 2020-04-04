package com.marcoscouto.services;

import com.marcoscouto.builders.MovieBuilder;
import com.marcoscouto.builders.UserBuilder;
import com.marcoscouto.dao.RentalDAO;
import com.marcoscouto.entities.Movie;
import com.marcoscouto.entities.Rental;
import com.marcoscouto.entities.User;
import com.marcoscouto.matchers.CustomMatchers;
import com.marcoscouto.utils.DateUtils;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RentalServiceTest_PowerMock {

    @InjectMocks @Spy
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

    @Test
    public void test() throws Exception {

        //Cenário
        User user = UserBuilder.oneUser().now();

        Mockito.doReturn(DateUtils.obtaingDate(03, 04, 2020)).when(rs).getDate();

        List<Movie> movies = new ArrayList<>();
        movies.addAll(Arrays.asList(
                MovieBuilder.oneMovie().price(5.0).now()
        ));

        //Ação

        Rental rental = rs.rentMovie(user, movies);

        //Verificação

        errorCollector.checkThat(rental.getPrice().doubleValue(), CoreMatchers.is(5.0));
        errorCollector.checkThat(DateUtils.isSameDate(rental.getInitialDate(), DateUtils.obtaingDate(03, 04, 2020)), CoreMatchers.is(true));
        errorCollector.checkThat(DateUtils.isSameDate(rental.getFinalDate(), DateUtils.obtaingDate(04, 04, 2020)), CoreMatchers.is(true));
    }

    @Test
    // @Ignore("Test ignored, it works on saturdays")
    public void shouldGiveBackMovieOnMondayInsteadSunday() throws Exception {

        //Cenário
        User user = UserBuilder.oneUser().now();
        List<Movie> movies = Arrays.asList(
                MovieBuilder.oneMovie().now()
        );

        Mockito.doReturn(DateUtils.obtaingDate(04, 04, 2020)).when(rs).getDate();


        //Ação
        Rental rental = rs.rentMovie(user, movies);

        //Verificação
        Assert.assertThat(rental.getFinalDate(), CustomMatchers.atMonday());
    }

    @Test
    public void shouldCalculateRentalTotal() throws Exception {
        //Cenário
        List<Movie> movies = Arrays.asList(MovieBuilder.oneMovie().now());

        //Ação
        Class<RentalService> clss = RentalService.class;
        Method method = clss.getDeclaredMethod("calcRentalTotal", List.class);
        method.setAccessible(true);
        Double total = (Double) method.invoke(rs, movies);

        //Verificação
        Assert.assertEquals(4.0,  total.doubleValue(), 0);
    }

    @Test
    public void shouldRentMovieWithoutCalcTotal() throws Exception {
        //Cenário
        User user = UserBuilder.oneUser().now();
        List<Movie> movies = Arrays.asList(MovieBuilder.oneMovie().now());

        Mockito.doReturn(1.0).when(rs).calcRentalTotal(movies);

        //Ação
        Rental rental = rs.rentMovie(user, movies);

        //Verificação
        Assert.assertThat(rental.getPrice(), CoreMatchers.is(1.0));
    }

}
