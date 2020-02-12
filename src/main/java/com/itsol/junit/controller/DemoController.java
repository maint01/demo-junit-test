package com.itsol.junit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/")
    public String goToIndex(Model model){
        model.addAttribute("appName", "JUNIT-TEST");
        model.addAttribute("someText", "someText");
        return "index";
    }
    @GetMapping("/home")
    public String goToHomePage(){
        return "home";
    }
}
