package com.marcoscouto.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class CalculatorMockTest {

    @Mock
    private Calculator calcMock;

    @Spy
    private Calculator calcSpy;

    @Mock
    private EmailService emailService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldShowDifferenceBetweenMockAndSpy(){
        Mockito.when(calcMock.sum(1, 2)).thenReturn(5);
        //Mockito.when(calcMock.sum(1, 2)).thenCallRealMethod();
        //Mockito.when(calcSpy.sum(1, 2)).thenReturn(5);
        Mockito.doReturn(5).when(calcSpy).sum(1, 2);
        Mockito.doNothing().when(calcSpy).print();

//        System.out.println("Mock: " + calcMock.sum(1, 2));
//        System.out.println("Spy: " + calcSpy.sum(1, 2));
//
//        System.out.println("Mock");
//        calcMock.print();
//        System.out.println("Spy");
//        calcSpy.print();
    }

    @Test
    public void test(){

        Calculator calculator = Mockito.mock(Calculator.class);

        ArgumentCaptor<Integer> argCapt = ArgumentCaptor.forClass(Integer.class);
        Mockito.when(calculator.sum(argCapt.capture(), argCapt.capture())).thenReturn(5);

        Assert.assertEquals(5, calculator.sum(1, 10000));
//        System.out.println(argCapt.getAllValues());
    }
}
