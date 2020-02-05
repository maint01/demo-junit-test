package com.itsol.junit.rule;

import com.itsol.junit.string.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class CustomRule implements TestRule {
    private String source;
    private Statement base;
    private Description description;

    @Override
    public Statement apply(Statement base, Description description) {
        this.base = base;
        this.description = description;
        return new MyStatement(base, description);
    }
    public void setSource(String source){
        this.source = source;
    }
    public void standardString(){
        source = new StringUtil().standardString(source);
    }
    public boolean isMatched(String s){
        return StringUtils.trimToEmpty(s).equals(source);
    }
}
