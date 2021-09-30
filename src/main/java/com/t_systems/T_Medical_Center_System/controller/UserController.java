package com.t_systems.T_Medical_Center_System.controller;

import com.t_systems.T_Medical_Center_System.service.impl.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private UserServiceImp userService;

    @Autowired
    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam(value = "id") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin";
    }

}
