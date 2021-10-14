package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.service.impl.AppointmentServiceImp;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppointmentController {
    private AppointmentServiceImp appointmentServiceImp;
    private PatientServiceImp patientServiceImp;
    private AppointmentRepository appointmentRepository;




    @Autowired
    public AppointmentController(AppointmentServiceImp appointmentServiceImp, PatientServiceImp patientServiceImp, AppointmentRepository appointmentRepository) {
        this.appointmentServiceImp = appointmentServiceImp;
        this.patientServiceImp = patientServiceImp;
        this.appointmentRepository = appointmentRepository;
    }


    @GetMapping(value = "/patient/profile/{id}/appointment")
    public String newAppointmentGet(@PathVariable(name = "id")Long id, Model model) {
        PatientDto patientDto = patientServiceImp.getPatientById(id);


        model.addAttribute("patient",patientDto);
        model.addAttribute("appointmentNew",new AppointmentDto());

        return "templates/appointment";


    }
    @PostMapping(value = "/patient/profile/{id}/appointment")
    public String newAppointmentPost(@PathVariable(name = "id")Long id, @ModelAttribute(value = "appointmentNew") AppointmentDto appointmentDto) {
        appointmentServiceImp.addAppointment(appointmentDto);
        return "redirect:/patient/profile/{id}";

    }



}
