package com.itsol.junit.rule;

import com.itsol.junit.operator.Operator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RuleExceptionTesterExample {
    @SuppressWarnings({"deprecation"})
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void throwsIllegalArgumentExceptionIfIconIsNull() {
        exception.expect(ArithmeticException.class);
        exception.expectMessage("Test");
        Operator t = new Operator();
        t.division(-1, 0);
    }
}
