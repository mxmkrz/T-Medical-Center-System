package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.service.impl.MedicalStaffServiceImp;
import com.t_systems.t_medical_center_system.util.AdminValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationMedicalStaffController {
    private final MedicalStaffServiceImp medicalStaffServiceImp;
    private final AdminValidator adminValidator;

    @Autowired
    public RegistrationMedicalStaffController(MedicalStaffServiceImp medicalStaffServiceImp, AdminValidator adminValidator) {
        this.medicalStaffServiceImp = medicalStaffServiceImp;
        this.adminValidator = adminValidator;
    }


    //*******************************************
    @GetMapping("/menu/registration")
    public String addStaffGet(Model model) {
        model.addAttribute("staff", new MedicalStaff());
        return "templates/registration";
    }

    @PostMapping(value = "/menu/registration")
    public String addStaffPost(@ModelAttribute("staff") @Valid MedicalStaff doctor,
                               BindingResult bindingResult) {
        adminValidator.validate(doctor, bindingResult);
        if (bindingResult.hasErrors()) {
            return "templates/registration";
        }
        medicalStaffServiceImp.saveStaff(doctor);
        return "redirect:/logout";
    }

    @GetMapping("/menu")
    public String navForAdmin() {
        return "templates/nav";
    }


}
