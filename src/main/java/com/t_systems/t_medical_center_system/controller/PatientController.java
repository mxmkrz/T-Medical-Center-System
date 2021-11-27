package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
import com.t_systems.t_medical_center_system.util.PatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class PatientController {

    private final PatientServiceImp patientService;
    private final PatientValidator patientValidator;
    @Autowired
    public PatientController(PatientServiceImp patientService, PatientValidator patientValidator) {
        this.patientService = patientService;
        this.patientValidator = patientValidator;
    }





    @GetMapping("/doctor/patients")
    public String getPatientsList(Model model
    ,@PageableDefault(size = 4,sort = {"createDataTime"},direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PatientDto> pages = patientService.getAllPatients(pageable);
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalElements", pages.getTotalElements());
        model.addAttribute("size", pages.getSize());
        model.addAttribute("patients", pages.getContent());

        return "templates/patients";
    }
    //*******************************************

    @GetMapping("/doctor/add")
    public String addPatientGet(Model model) {
        model.addAttribute("patient", new PatientDto());
        return "templates/addPatient";
    }


    @PostMapping(value = "/doctor/add")
    public String addPatientPost(@ModelAttribute("patient") @Valid PatientDto patientDto, BindingResult bindingResult) {
        patientValidator.validate(patientDto,bindingResult);
        if (bindingResult.hasErrors()) {
            return "/templates/addPatient";
        }
        patientService.savePatient(patientDto);
        return "redirect:/doctor/patients";
    }


    //*******************************************
    @GetMapping("/doctor/profile/{id}")
    public String getPatientProfile(@PathVariable("id") Long id, Model model) {
        model.addAttribute("profile", patientService.getPatientById(id));
        return "templates/profile";
    }


    //*******************************************
    @GetMapping(value = "/doctor/profile/{id}/edit")
    public String updatePatientGet(Model model, @PathVariable Long id) {
        model.addAttribute("profile", patientService.getPatientById(id));
        return "templates/editPatient";
    }


    @PostMapping(value = "/doctor/profile/{id}/edit")
    public String updatePatientPost(@ModelAttribute("profile") @Valid PatientDto patientDto, BindingResult bindingResult) {
        patientValidator.validate(patientDto,bindingResult);
        if (bindingResult.hasErrors()) {
            return "templates/editPatient";
        }
        patientService.discharge(patientDto);
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
