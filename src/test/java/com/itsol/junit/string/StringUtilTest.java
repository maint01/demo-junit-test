package com.itsol.junit.string;

import junit.framework.TestCase;
import org.junit.Assert;

public class StringUtilTest extends TestCase {
    private StringUtil stringUtil;

    @Override
    protected void setUp() throws Exception {
        stringUtil = new StringUtil();
    }

    public void testStandardString_OK(){
        String s = "tRan VAn nguyen";
        assertEquals("Tran Van Nguyen", stringUtil.standardString(s));
    }

    public void testStandardString_IsNull_OK(){
        assertEquals("", stringUtil.standardString(null));
    }

    public void testStandardString_IsEmpty_OK(){
        assertEquals("", stringUtil.standardString(""));
    }

    public void testFindCharacterNotMatchRegex_OK(){
        String s = "tRan VAn nguyen000";
        assertEquals("tRan VAn nguyen", stringUtil.findCharacterNotMatchRegex(s,"[0-9]"));
    }

    public void testFindCharacterNotMatchRegex_RegexIsNull_OK(){
        String s = "tRan VAn nguyen000";
        assertEquals("", stringUtil.findCharacterNotMatchRegex(s,null));
    }

    public void testAssertArrayEquals(){
        int[] arr1 = new int[]{1,3,4,5};
        int[] arr2 = new int[]{1,3,4,5};
        Assert.assertArrayEquals(arr1, arr2);
    }

}
