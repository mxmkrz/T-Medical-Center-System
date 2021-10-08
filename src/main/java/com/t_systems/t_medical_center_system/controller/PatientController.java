package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/patient")
public class PatientController {

    private final PatientServiceImp patientService;

    @Autowired
    public PatientController(@NonNull PatientServiceImp patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/patients")
    public String getPatientsList(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "templates/patients";
    }
    //*******************************************

    @GetMapping("/add")
    public String addPatientGet(Model model) {
        model.addAttribute("patient", new Patient());
        return "templates/addPatient";
    }


    @PostMapping(value = "/add")
    public String addPatientPost(@ModelAttribute("patient") Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/patient/patients";
    }




    //*******************************************
    @GetMapping("/profile/{id}")
    public String getPatientProfile(@PathVariable("id") Long id, Model model) {
        model.addAttribute("profile", patientService.getPatientById(id));
        return "templates/profile";
    }


    //*******************************************
    @GetMapping(value = "/edit")
    public String updatePatientGet(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("profile", patientService.getPatientById(id));
        return "templates/editPatient";
    }

    @PostMapping(value = "/edit")
    public String updatePatientPost(@ModelAttribute(name = "profile") PatientDto patientDto) {
        patientService.updatePatient(patientDto);
        Long id = patientDto.getId();
         return "redirect:/patient/profile/"+id;
    }

    //*******************************************
    @GetMapping(value = "/delete")
    public String deletePatient(@RequestParam(name = "id") Long patientId) {
        patientService.deletePatient(patientId);
        return "redirect:/patient/patients";
    }







}
