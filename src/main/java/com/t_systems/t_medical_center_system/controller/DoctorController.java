package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.dto.DoctorDto;
import com.t_systems.t_medical_center_system.entity.Doctor;
import com.t_systems.t_medical_center_system.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DoctorController {

    private final DoctorService doctorService;
    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

//    @GetMapping("/registration")
//    public String registration(Model model) {
//        model.addAttribute("staff", new Doctor(authorities));
//        return "registration";
//    }

    @PostMapping("/registration")
    public String signUp(@ModelAttribute(name = "staff") DoctorDto doctor) {
        doctorService.add(doctor);
        return "redirect:/login";
    }

//    @GetMapping("/login")
//    public String login(Model model){
//        model.addAttribute("staff", new Doctor(authorities));
//        return "login";
//    }

    @GetMapping("/menu")
    public String menuForDoctor(){
        return "menu";
    }
    @PostMapping("/menu")
    public String menuForDoctorPost(){
        return "menu";
    }


}
