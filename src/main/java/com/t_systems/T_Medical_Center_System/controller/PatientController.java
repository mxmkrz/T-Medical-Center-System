package com.t_systems.T_Medical_Center_System.controller;

import com.t_systems.T_Medical_Center_System.dto.PatientDto;
import com.t_systems.T_Medical_Center_System.entity.Patient;
import com.t_systems.T_Medical_Center_System.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(@NonNull PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public String getPatientsList(Model model) {
        model.addAttribute("patients",patientService.findAll());
        return "patientsList";
    }

    @GetMapping(value = "/patient/{id}")
    public PatientDto getPatientById(@PathVariable("id") Long id) {
        return patientService.findById(id);
    }

    @PostMapping(value = "/add")
    public String addPatient(@ModelAttribute("patient") PatientDto patientDto) {


        log.info("New patient created");
        Patient patient = patientService.save(patientDto);
        patient.setFirstName(patientDto.getFirstName());
        patient.setSecondName(patientDto.getSecondName());
        patient.setDiagnosis(patientDto.getDiagnosis());
        patient.setInsuranceNumber(patientDto.getInsuranceNumber());
        patient.setDoctorsName(patientDto.getDoctorsName());
        patient.setStatus(patientDto.getStatus());
        return "addPatient";
    }

//    @PutMapping(value = "/pattients/{firstName}")
//    public void updatePatientByName(@RequestBody PatientDto patientDto,@PathVariable("firstName") String firstName) {
//        patientService.update(patientDto,firstName);
//    }

    @DeleteMapping(value = "/patients/{id}")
    public void deletePatient(@PathVariable("id") Long id) {
        patientService.deleteById(id);
    }


}
