package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.dto.DoctorDto;
import com.t_systems.t_medical_center_system.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DoctorController {

    private final MedicalStaffService doctorService;
    @Autowired
    public DoctorController(MedicalStaffService doctorService) {
        this.doctorService = doctorService;
    }



    @GetMapping("/allStaff")
    public String getDoctorList(Model model) {
        model.addAttribute("staff", doctorService.getAllDoctors());
        return "templates/allStaff";
    }

    //*******************************************
//    @GetMapping(value = "/doctor/{id}")
//    public DoctorDto getDoctorById(@PathVariable("id") Long id) {
//        return doctorService.getById(id);
//    }



    //*******************************************
    @GetMapping(value = "/editDoctor")
    public String updateDoctorGet(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("doctor", doctorService.getById(id));
        return "templates/editDoctor";
    }

    @PostMapping(value = "/editDoctor")
    public String updateDoctorPost(@ModelAttribute(name = "doctor") DoctorDto doctorDto) {
        doctorService.update(doctorDto);
        return "redirect:/allDoctors";
    }

    //*******************************************
    @GetMapping(value = "/deleteDoctor")
    public String deletePatient(@RequestParam(name = "id") Long patientId) {
        doctorService.delete(patientId);
        return "redirect:/allPatients";
    }


}
