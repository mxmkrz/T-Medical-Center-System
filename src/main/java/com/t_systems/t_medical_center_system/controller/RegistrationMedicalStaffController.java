package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.service.impl.MedicalStaffServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationMedicalStaffController {
    private final MedicalStaffServiceImp medicalStaffServiceImp;

    @Autowired
    public RegistrationMedicalStaffController(MedicalStaffServiceImp medicalStaffServiceImp) {
        this.medicalStaffServiceImp = medicalStaffServiceImp;
    }

    //*******************************************
    @GetMapping("/registrationMedicalStaff")
    public String addDoctorGet(Model model) {
        model.addAttribute("staff", new MedicalStaff());
        return "templates/registrationMedicalStaff";
    }

    @PostMapping(value = "/registrationMedicalStaff", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ModelAndView addPatientPost(@ModelAttribute("staff") MedicalStaff doctor) {
        medicalStaffServiceImp.saveStaff(doctor);
        return new ModelAndView("redirect:/login");
    }

}
