package com.itsol.junit.config;

import org.springframework.boot.SpringApplication;

import java.util.HashMap;
import java.util.Map;

public class DefaultProfileUtil {
    private static final String DEFAULT_PROPERTIES_PROFILE = "spring.profiles.active";
    public static void addDefaultProfile(SpringApplication app){
        Map<String, Object> defaultProperties= new HashMap<>();
        defaultProperties.put(DEFAULT_PROPERTIES_PROFILE, AppProfileConstants.PROFILE_DEVELOPMENT);
        app.setDefaultProperties(defaultProperties);
    }
}
