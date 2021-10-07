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
public class RegistrationNurseController {

    private MedicalStaffServiceImp medicalStaffServiceImp;

    @Autowired
    public RegistrationNurseController(MedicalStaffServiceImp medicalStaffServiceImp) {
        this.medicalStaffServiceImp = medicalStaffServiceImp;
    }

    //*******************************************
    @GetMapping("/registrationNurse")
    public String addDoctorGet(Model model) {
        model.addAttribute("nurse", new MedicalStaff());
        return "registrationNurse";
    }


    @PostMapping(value = "/registrationNurse")
    public String addPatientPost(@ModelAttribute("nurse") MedicalStaff nurse) {
        medicalStaffServiceImp.saveNurse(nurse);
        return "redirect:/allPatients";
    }
}
