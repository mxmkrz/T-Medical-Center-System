package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping
public class PatientController {

    private final PatientServiceImp patientService;

    @Autowired
    public PatientController(@NonNull PatientServiceImp patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/doctor/patients")
    public String getPatientsList(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "templates/patients";
    }
    //*******************************************

    @GetMapping("/doctor/add")
    public String addPatientGet(Model model) {
        model.addAttribute("patient", new PatientDto());
        return "templates/addPatient";
    }


    @PostMapping(value = "/doctor/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ModelAndView addPatientPost(@ModelAttribute("patient") PatientDto patientDto) {
        patientService.savePatient(patientDto);
        return new ModelAndView("redirect:/doctor/patients");
    }




    //*******************************************
    @GetMapping("/doctor/profile/{id}")
    public String getPatientProfile(@PathVariable("id") Long id, Model model) {
        model.addAttribute("profile", patientService.getPatientById(id));
        return "templates/profile";
    }


    //*******************************************
    @GetMapping(value = "/doctor/profile/{id}/edit")
    public String updatePatientGet( Model model, @PathVariable Long id  ) {
        model.addAttribute("profile", patientService.getPatientById(id));
        return "templates/editPatient";
    }


    @PostMapping(value = "/doctor/profile/{id}/edit")
    public String updatePatientPost(@ModelAttribute("profile") PatientDto patientDto) {
        patientService.updatePatient(patientDto);
        return "redirect:/doctor/profile/{id}";
    }

    //*******************************************
    @GetMapping(value = "/doctor/delete/{id}")
    public String deletePatient(@PathVariable(name = "id") Long id) {
        patientService.deletePatient(id);
        return "redirect:/doctor/patients";
    }
}
