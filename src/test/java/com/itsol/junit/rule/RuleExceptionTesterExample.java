package com.itsol.junit.rule;

import com.itsol.junit.operator.Operator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RuleExceptionTesterExample {
    private Operator t;
    @SuppressWarnings({"deprecation"})
    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Before
    public void setup(){
        t = new Operator();
    }

    @Test
    public void throwsArithmeticExceptionWithRule() {
        exception.expect(ArithmeticException.class);
        exception.expectMessage("Test");
        t.division(-1, 0);
    }

    @Test(expected = ArithmeticException.class)
    public void throwsArithmeticException() {
        t.division(-1, 0);
    }
}
