package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.service.impl.MedicalStaffServiceImp;
import com.t_systems.t_medical_center_system.util.StaffValidatorReset;
import com.t_systems.t_medical_center_system.util.StaffValidatorChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ChangePasswordController {


    private final MedicalStaffServiceImp medicalStaffServiceImp;
    private final StaffValidatorChange validatorChange;
    private final StaffValidatorReset validatorReset;

    @Autowired
    public ChangePasswordController(MedicalStaffServiceImp medicalStaffServiceImp, StaffValidatorChange staffValidator, StaffValidatorReset staffValidatorChange) {
        this.medicalStaffServiceImp = medicalStaffServiceImp;
        this.validatorChange = staffValidator;
        this.validatorReset = staffValidatorChange;
    }


    //*******************************************
    @GetMapping("/login/reset")
    public String sendLinkEmail() {
        return "templates/resetPassword";
    }


    @PostMapping(value = "/login/reset")
    public String sendLinkEmail(@ModelAttribute("sendEmail")
                                @Valid MedicalStaff medicalStaff
            , BindingResult bindingResult) {
        validatorReset.validate(medicalStaff,bindingResult);
        if (bindingResult.hasErrors()) {
            return "/templates/resetPassword";
        }
        medicalStaffServiceImp.sendLinkOnEmail(medicalStaff.getEmail());
        return "redirect:/login";
    }

    //*******************************************
    @GetMapping("/login/change")
    public String changePassword() {
        return "templates/changePassword";
    }


    @PostMapping(value = "/login/change")
    public String changePassword(@ModelAttribute("changePassword")
                                 @Valid MedicalStaff medicalStaff
            , BindingResult bindingResult) {

        validatorChange.validate(medicalStaff, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/templates/changePassword";
        }
        medicalStaffServiceImp.changePassword(medicalStaff);
        return "redirect:/login";
    }


}
