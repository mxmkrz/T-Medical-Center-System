package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
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

    @GetMapping("/allPatients")
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
    @GetMapping("/addPatient")
    public String addPatientGet(Model model) {
        model.addAttribute("patient", new Patient());
        return "addPatient";
    }


    @PostMapping(value = "/addPatient")
    public String addPatientPost(@ModelAttribute("patient") Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/allPatients";
    }

    //*******************************************
    @GetMapping(value = "/editPatient")
    public String updatePatientGet(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        return "editPatient";
    }

    @PostMapping(value = "/editPatient")
    public String updatePatientPost(@ModelAttribute(name = "patient") PatientDto patientDto) {
        patientService.updatePatient(patientDto);
        return "redirect:/allPatients";
    }

    //*******************************************
    @GetMapping(value = "/deletePatient")
    public String deletePatient(@RequestParam(name = "id") Long patientId) {
        patientService.deletePatient(patientId);
        return "redirect:/allPatients";
    }


}
