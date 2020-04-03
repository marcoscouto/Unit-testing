package com.marcoscouto.services;

import org.junit.Test;
import org.mockito.Mockito;

public class CalculatorMockTest {

    @Test
    public void test(){
        Calculator calculator = Mockito.mock(Calculator.class);
        Mockito.when(calculator.sum(Mockito.eq(1), Mockito.anyInt())).thenReturn(5);

        System.out.println(calculator.sum(2, 9));
    }
}
