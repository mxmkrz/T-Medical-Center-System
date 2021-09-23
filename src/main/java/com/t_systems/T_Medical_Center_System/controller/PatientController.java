package com.t_systems.T_Medical_Center_System.controller;

import com.t_systems.T_Medical_Center_System.dto.PatientDto;
import com.t_systems.T_Medical_Center_System.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(@NonNull PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "/all")
    public List<PatientDto> getPatientsList() {
        return patientService.findAll();
    }

    @GetMapping(value = "/patient/{id}")
    public PatientDto getPatientById(@PathVariable("id") Long id) {
        return patientService.findById(id);
    }

    @PostMapping(value = "/addPatient")
    public void addPatient(@RequestBody PatientDto patientDto) {
        patientService.save(patientDto);
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
