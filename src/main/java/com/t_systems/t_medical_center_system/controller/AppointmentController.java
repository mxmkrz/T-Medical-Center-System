package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.service.impl.AppointmentServiceImp;
import com.t_systems.t_medical_center_system.service.impl.EventServiceImp;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class AppointmentController {
    private AppointmentServiceImp appointmentServiceImp;
    private PatientServiceImp patientServiceImp;
    private AppointmentRepository appointmentRepository;
    private EventServiceImp eventServiceImp;

    @Autowired
    public AppointmentController(AppointmentServiceImp appointmentServiceImp, PatientServiceImp patientServiceImp, AppointmentRepository appointmentRepository, EventServiceImp eventServiceImp) {
        this.appointmentServiceImp = appointmentServiceImp;
        this.patientServiceImp = patientServiceImp;
        this.appointmentRepository = appointmentRepository;
        this.eventServiceImp = eventServiceImp;
    }





    @GetMapping(value = "/patient/profile/{id}/appointment")
    public String newAppointmentGet(@PathVariable(name = "id")Long id, Model model) {
        model.addAttribute("patient",patientServiceImp.getPatientById(id));
        model.addAttribute("appointmentNew",new AppointmentDto());
        return "templates/appointment";
    }


    @PostMapping(value = "/patient/profile/{id}/appointment")
    public String newAppointmentPost(@PathVariable(name = "id")Long id,
                                      @ModelAttribute(value = "appointmentNew") AppointmentDto appointmentDto) {
        eventServiceImp.generateEvents(appointmentDto);
        appointmentServiceImp.addAppointment(appointmentDto,id);
        return "redirect:/patient/profile/{id}";
    }




    @GetMapping(value = "/patient/profile/{id}/pageAppointment")
    public String getAppointmentPage(@PathVariable(name = "id")Long id, Model model) {
        model.addAttribute("pageAppointment",appointmentServiceImp.getAppointmentListByPatient(id));
        model.addAttribute("patient",patientServiceImp.getPatientById(id));
        return "templates/pageAppointment";
    }


    //*******************************************
    @GetMapping(value = "/patient/profile/{id}/edit/{idAppointment}")
    public String editAppointmentGet(@PathVariable(name = "id")Long id,
                                     @PathVariable(name = "idAppointment")Long idAppointment, Model model) {
        model.addAttribute("patient",patientServiceImp.getPatientById(id));
        model.addAttribute("editAppointment",appointmentServiceImp.gitAppointById(idAppointment));
        return "templates/appointmentEdit";
    }


    @PostMapping(value = "/patient/profile/{id}/edit/{idAppointment}")
    public String editAppointmentPost(@PathVariable(name = "id")Long id,
                                      @PathVariable(name = "idAppointment")Long idAppointment, @ModelAttribute(value = "appointmentEdit") AppointmentDto appointmentDto) {
        appointmentServiceImp.updateAppointment(appointmentDto,id);
        return "redirect:/patient/profile/{id}";
    }













}
