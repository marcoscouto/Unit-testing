package com.marcoscouto.services;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderTest {

    // Control test order is unusual, the test has to be independent

    public static int cont = 0;

    @Test
    public void first(){
        cont = 1;
    }

    @Test
    public void second(){
        Assert.assertEquals(1, cont);
    }

}
