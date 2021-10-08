package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.service.impl.AppointmentServiceImp;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    private AppointmentServiceImp appointmentServiceImp;
    private PatientServiceImp patientServiceImp;

    @Autowired
    public AppointmentController(AppointmentServiceImp appointmentServiceImp, PatientServiceImp patientServiceImp) {
        this.appointmentServiceImp = appointmentServiceImp;
        this.patientServiceImp = patientServiceImp;
    }

//    @GetMapping("/profile/{id}")
//    public String addAppointment(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("profile", patientService.getAppointment(id));
//        return "/appointment/appointments";
//    }
////
//    @GetMapping(value = "/edit")
//    public String updatePatientGet(@RequestParam(name = "id") Long id, Model model) {
//        model.addAttribute("profile", patientService.getPatientById(id));
//        return "editPatient";
//    }
//
//    @PostMapping(value = "/edit")
//    public String updatePatientPost(@ModelAttribute(name = "profile") PatientDto patientDto) {
//        patientService.updatePatient(patientDto);
//        Long id = patientDto.getId();
//        return "redirect:/patient/profile/"+id;
//    }




}
