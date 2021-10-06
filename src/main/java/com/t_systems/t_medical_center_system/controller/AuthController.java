package com.t_systems.t_medical_center_system.controller;


import com.t_systems.t_medical_center_system.entity.User;
import com.t_systems.t_medical_center_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "/sign_up";
    }

    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute(name = "user") @Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "/sign_up";
        }
        userService.add(user);
        return "redirect:/";
    }

}