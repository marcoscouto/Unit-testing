package com.marcoscouto.suite;

import com.marcoscouto.services.CalcRentalValueTest;
import com.marcoscouto.services.CalculatorTest;
import com.marcoscouto.services.RentalServiceTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.runners.Suite.*;

@RunWith(value = Suite.class)
@SuiteClasses({
        CalculatorTest.class,
        CalcRentalValueTest.class,
        RentalServiceTest.class
})
public class SuiteTest {

    @AfterClass
    public static void after(){
        System.out.println("After");
    }

    @BeforeClass
    public static void before(){
        System.out.println("Before");
    }


}
