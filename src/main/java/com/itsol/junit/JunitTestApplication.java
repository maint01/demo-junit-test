package com.itsol.junit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SpringBootConfiguration
public class JunitTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(JunitTestApplication.class, args);
    }
}
