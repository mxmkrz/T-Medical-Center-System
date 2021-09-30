package com.t_systems.T_Medical_Center_System.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/home")
    public String getHomePage(){
        return "homePage";
    }

}
