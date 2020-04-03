package com.marcoscouto.services;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class CalculatorMockTest {

    @Test
    public void test(){
        Calculator calculator = Mockito.mock(Calculator.class);

        ArgumentCaptor<Integer> argCapt = ArgumentCaptor.forClass(Integer.class);
        Mockito.when(calculator.sum(argCapt.capture(), argCapt.capture())).thenReturn(5);

        Assert.assertEquals(5, calculator.sum(1, 10000));
        System.out.println(argCapt.getAllValues());
    }
}
