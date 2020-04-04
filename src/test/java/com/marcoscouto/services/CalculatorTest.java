package com.marcoscouto.services;

import com.marcoscouto.exceptions.DivisionNotSupportedException;
import com.marcoscouto.runners.ParallelRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ParallelRunner.class)
public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setup(){
        calculator = new Calculator();
    }

    @Test
    public void sumTwoValues(){
        //Cenário
        int a = 5;
        int b = 3;

        //Ação
        int result = calculator.sum(a, b);

        //Verificação
        Assert.assertEquals(8, result);
    }

    @Test
    public void subtractTwoValues(){
        //Cenário
        int a = 8;
        int b = 5;
        Calculator calculator = new Calculator();

        //Ação
        int result = calculator.subtract(a, b);

        //Verificação
        Assert.assertEquals(3, result);
    }

    @Test
    public void divisionTwoValues() throws DivisionNotSupportedException {
        //Cenário
        int a = 6;
        int b = 3;

        //Ação
        int result = calculator.division(a, b);

        //Verificação
        Assert.assertEquals(2, result);
    }

    @Test(expected = DivisionNotSupportedException.class)
    public void exceptionDivisionPerZero() throws DivisionNotSupportedException {
        //Cenário
        int a = 10;
        int b = 0;


        //Ação
        calculator.division(a, b);
    }

}
