package com.itsol.junit;

import com.itsol.junit.config.DefaultProfileUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.SimpleCommandLinePropertySource;

@SpringBootApplication
@SpringBootConfiguration
public class JunitTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(JunitTestApplication.class, args);
    }
}
