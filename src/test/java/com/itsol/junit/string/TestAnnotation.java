package com.itsol.junit.string;

import org.junit.*;

public class TestAnnotation {
    @Before
    public void setup(){
        System.out.println("Before");
    }
    @BeforeClass
    public static void beforeClass(){
        System.out.println("BeforeClass");
    }

    @After
    public void after(){
        System.out.println("After");
    }
    @AfterClass
    public static void afterClass(){
        System.out.println("afterClass");
    }

    @Test
    public void test1(){
        System.out.println("Testing 1");
    }

    @Test
    public void test2(){
        System.out.println("Testing 2");
    }
    @Test(timeout = 100)
    public void testTimeout(){
        for(int i=0;i<1000000000;i++){
        }
    }
}
