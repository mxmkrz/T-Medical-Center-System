package com.t_systems.t_medical_center_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {


    @GetMapping("/")
    public String startPage() {
        return "redirect:/login";
    }

}
