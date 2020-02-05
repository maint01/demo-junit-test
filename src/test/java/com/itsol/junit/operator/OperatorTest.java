package com.itsol.junit.operator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OperatorTest {
    private Operator operator;
    @Before
    public void beforeTest(){
        operator = new Operator();
    }
    @Test
    public void sumGood(){
        Assert.assertEquals(operator.sum(5,6), 11);
    }
    @Test
    public void mulGood(){
        Assert.assertEquals(operator.multiplication(1, 4),4);
    }
    @Test
    public void subGood(){
        Assert.assertEquals(operator.subtraction(5,6), -1);
    }
    @Test
    public void divGood(){
        boolean isTrue = Double.compare(operator.division(6,3), 2d) == 0;
        Assert.assertTrue(isTrue);
    }

}
