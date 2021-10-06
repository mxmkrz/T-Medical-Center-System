package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.dto.DoctorDto;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DoctorController {

    private final DoctorService doctorService;
    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }



    @GetMapping("/allDoctors")
    public String getDoctorList(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctorsList";
    }

    //*******************************************
    @GetMapping(value = "/doctor/{id}")
    public DoctorDto getDoctorById(@PathVariable("id") Long id) {
        return doctorService.getById(id);
    }

    //*******************************************
    @GetMapping("/addDoctor")
    public String addDoctorGet(Model model) {
        model.addAttribute("doctor", new MedicalStaff());
        return "addDoctor";
    }


    @PostMapping(value = "/addDoctor")
    public String addPatientPost(@ModelAttribute("doctor") MedicalStaff doctor) {
        doctorService.saveDoctor(doctor);
        return "redirect:/allDoctors";
    }

    //*******************************************
    @GetMapping(value = "/editDoctor")
    public String updateDoctorGet(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("doctor", doctorService.getById(id));
        return "editDoctor";
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
        return "redirect:/allDoctors";
    }


}
