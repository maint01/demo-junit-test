package com.itsol.junit.string;

import junit.framework.TestCase;

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

    public void testFindCharacterNotMatchRegex_OK(){
        String s = "tRan VAn nguyen000";
        assertEquals("tRan VAn nguyen", stringUtil.findCharacterNotMatchRegex(s,"[0-9]"));
    }
}
