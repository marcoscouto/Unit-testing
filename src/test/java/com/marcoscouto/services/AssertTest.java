package com.marcoscouto.services;

import com.marcoscouto.entities.User;
import org.junit.Assert;
import org.junit.Test;

public class AssertTest {

    @Test
    public void test() {
        Assert.assertTrue(true);
        Assert.assertFalse(false);

        // (Expected, Actual)
        Assert.assertEquals(1, 1);

        // Number, Number, Margin of Error
        Assert.assertEquals(0.51234, 0.512, 0.001);
        Assert.assertEquals(Math.PI, 3.14, 0.01);

        int i = 5;
        Integer i2 = 5;
        Assert.assertEquals(Integer.valueOf(i), i2);
        Assert.assertEquals(i, i2.intValue());

        Assert.assertEquals("bola", "bola");
//      Assert.assertEquals("bola", "Bola"); // Error
        Assert.assertTrue("Bola".equalsIgnoreCase("Bola"));
        Assert.assertTrue("bola".startsWith("bo"));

        User u1 = new User("Marcos");
        User u2 = new User("Marcos");
        User u3 = u2;
        User u4 = null;

        Assert.assertEquals(u1, u2); // Error if doesn't have equals
        Assert.assertEquals(u2, u3);
        Assert.assertEquals(u1, u3);

        Assert.assertNull(u4);

//      Assert.assertSame(u1, u2); // Error
        Assert.assertSame(u2, u3);

//      Negatives
//      Assert.assertNotEquals();
//      Assert.assertNotSame();
//      Assert.assertNotNull();

//        Deprecated
//        Assert.assertThat();

    }

}
