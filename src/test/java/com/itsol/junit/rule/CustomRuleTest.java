package com.itsol.junit.rule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class CustomRuleTest {
    @Rule
    public CustomRule customRule = new CustomRule();
    @Test
    public void test(){
        customRule.setSource("NGUYEN    VAN   A    ");
        customRule.standardString();
        boolean isMatched = customRule.isMatched("Nguyen Van A");
        Assert.assertTrue(isMatched);
    }
}
