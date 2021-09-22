package com.t_systems.T_Medical_Center_System.controller;

import com.t_systems.T_Medical_Center_System.dto.PatientDto;
import com.t_systems.T_Medical_Center_System.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "/patients")
    public List<PatientDto> getPatientsList() {
        return patientService.findAll();
    }

    @GetMapping(value = "/patient/{id}")
    public PatientDto getPatientById(@PathVariable("id") Long id) {
        return patientService.findById(id);
    }

    @PostMapping(value = "/addPatient")
    public void addPatient(@RequestBody PatientDto patientDto) {
        log.info("patient add" ,patientDto);
        patientService.save(patientDto);
    }

    @PutMapping(value = "/pattients")
    public void updatePatient(@RequestBody PatientDto patientDto) {
        patientService.update(patientDto);
    }

    @DeleteMapping(value = "/patients/{id}")
    public void deletePatient(@PathVariable("id") Long id) {
        patientService.deleteById(id);
    }


}
