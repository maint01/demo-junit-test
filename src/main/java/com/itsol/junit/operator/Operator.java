package com.itsol.junit.operator;

public class Operator {
    public int sum(int a, int b){
        return a + b;
    }
    public int subtraction(int a,int b){
        return a - b;
    }
    public long multiplication(int a, int b) {
        return a * b;
    }
    public double  division(int a, int b) throws ArithmeticException {
        if(b == 0)
            throw new ArithmeticException("Test");
        return a/(double)b;
    }
}
