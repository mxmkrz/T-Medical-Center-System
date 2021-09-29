package com.t_systems.T_Medical_Center_System.controller;

import com.t_systems.T_Medical_Center_System.dto.PatientDto;
import com.t_systems.T_Medical_Center_System.entity.Patient;
import com.t_systems.T_Medical_Center_System.service.impl.PatientServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
public class PatientController {

    private final PatientServiceImp patientService;

    @Autowired
    public PatientController(@NonNull PatientServiceImp patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public String getPatientsList(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patientsList";
    }

    //*******************************************
    @GetMapping(value = "/patient/{id}")
    public PatientDto getPatientById(@PathVariable("id") Long id) {
        return patientService.getPatientById(id);
    }

    //*******************************************
    @GetMapping("/add")
    public String addCustomerGet(Model model) {
        model.addAttribute("patient", new Patient());
        return "addPatient";
    }


    @PostMapping(value = "/add")
    public String addPatientPost(@ModelAttribute("patient") PatientDto patientDto) {
        log.info("New patient created");
        patientService.savePatient(patientDto);
        return "redirect:/all";
    }

    //*******************************************
    @GetMapping(value = "/edit")
    public String updatePatientGet(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        return "editPatient";
    }

    @PostMapping(value = "/edit")
    public String updatePatientPost(@ModelAttribute(name = "patient") PatientDto patientDto) {
        patientService.updatePatient(patientDto);
        return "redirect:/all";
    }

    //*******************************************
    @GetMapping(value = "/deletePatient")
    public String deletePatient(@RequestParam(name = "id") Long patientId) {
        patientService.deletePatient(patientId);
        return "redirect:/all";
    }


}
