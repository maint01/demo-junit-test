package com.itsol.junit.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringUtilTest {
    private StringUtil stringUtil;
    @Before
    public void beforeMethod(){
        stringUtil = new StringUtil();
    }

    @Test
    public void caseGood(){
        String s = "tRan VAn nguyen";
        Assert.assertEquals("Tran Van Nguyen", stringUtil.standardString(s));
    }
}