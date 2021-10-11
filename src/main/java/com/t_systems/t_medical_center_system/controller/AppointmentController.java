package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.service.impl.AppointmentServiceImp;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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




    @GetMapping("/patient/profile/{id}/appointment")
    public String addAppointmentGet(@PathVariable(name ="id") Long id, Model model) {


        model.addAttribute("appointment",new AppointmentDto());
        return "templates/appointment";
    }

    @PostMapping(value = "/patient/profile/{id}/appointment", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addAppointment(@PathVariable(name ="id") Long id,@ModelAttribute("appointment") AppointmentDto appointment) {
        appointmentServiceImp.addAppointment(appointment,id);
        return "Yes";
    }




}
