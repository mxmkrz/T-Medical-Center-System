package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.service.impl.MedicalStaffServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationDoctorController {
    private final MedicalStaffServiceImp medicalStaffServiceImp;

    @Autowired
    public RegistrationDoctorController(MedicalStaffServiceImp medicalStaffServiceImp) {
        this.medicalStaffServiceImp = medicalStaffServiceImp;
    }

    //*******************************************
    @GetMapping("/registrationDoctor")
    public String addDoctorGet(Model model) {
        model.addAttribute("doctor", new MedicalStaff());
        return "templates/registrationDoctor";
    }


    @PostMapping(value = "/registrationDoctor")
    public String addPatientPost(@ModelAttribute("doctor") MedicalStaff doctor) {
        medicalStaffServiceImp.saveDoctor(doctor);
        return "redirect:/allDoctors";
    }

}
