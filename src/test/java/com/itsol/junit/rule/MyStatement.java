package com.itsol.junit.rule;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyStatement extends Statement {
    private Logger log = LoggerFactory.getLogger(getClass());
    private final Statement base;
    private final Description description;

    public MyStatement(Statement base, Description description) {
        this.base = base;
        this.description = description;
    }

    @Override
    public void evaluate() throws Throwable {
        log.trace("MyCustomRule", description.getMethodName() + "Started");
        try {
            base.evaluate();
        } finally {
            log.trace("MyCustomRule", description.getMethodName() + "Finished");
        }
    }
}