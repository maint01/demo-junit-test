package com.itsol.junit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/")
    public String goToIndex(){
        return "index";
    }
    @GetMapping("/home")
    public String goToHomePage(){
        return "home";
    }
}
