package com.t_systems.t_medical_center_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.service.impl.AppointmentServiceImp;
import com.t_systems.t_medical_center_system.service.impl.EventServiceImp;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AppointmentController {
    private AppointmentServiceImp appointmentServiceImp;
    private PatientServiceImp patientServiceImp;
    private ObjectMapper objectMapper = new ObjectMapper();
    private EventServiceImp eventServiceImp;

    @Autowired
    public AppointmentController(AppointmentServiceImp appointmentServiceImp, PatientServiceImp patientServiceImp, EventServiceImp eventServiceImp) {
        this.appointmentServiceImp = appointmentServiceImp;
        this.patientServiceImp = patientServiceImp;
        this.eventServiceImp = eventServiceImp;
    }

    @PostConstruct
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
    }


    @GetMapping(value = "/doctor/profile/{id}/appointment")
    public String newAppointmentGet(@PathVariable(name = "id") Long id,
                                    Model model) {
        model.addAttribute("patient", patientServiceImp.getPatientById(id));
        model.addAttribute("appointmentNew", new AppointmentDto());
        return "templates/appointment";
    }


    @PostMapping(value = "/doctor/profile/{id}/appointment")
    public String newAppointmentPost(@PathVariable(name = "id") Long id,
                                     @ModelAttribute(value = "appointmentNew") AppointmentDto appointmentDto) {

        appointmentServiceImp.addAppointment(appointmentDto, id);


        return "redirect:/doctor/profile/{id}";
    }


    @GetMapping(value = "/doctor/profile/{id}/pageAppointment")
    public String getAppointmentPage(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("pageAppointment", appointmentServiceImp.getAppointmentListByPatient(id));
        model.addAttribute("patient", patientServiceImp.getPatientById(id));
        return "templates/pageAppointment";
    }


    //*******************************************
    @GetMapping(value = "/doctor/profile/{id}/edit/{idAppointment}")
    public String editAppointmentGet(@PathVariable(name = "id") Long id,
                                     @PathVariable(name = "idAppointment") Long idAppointment, Model model) {
        model.addAttribute("patient", patientServiceImp.getPatientById(id));
        model.addAttribute("editAppointment", appointmentServiceImp.gitAppointById(idAppointment));
        return "templates/appointmentEdit";
    }


    @PostMapping(value = "/doctor/profile/{id}/edit/{idAppointment}")
    public String editAppointmentPost(@PathVariable(name = "id") Long id,
                                      @PathVariable(name = "idAppointment") Long idAppointment, @ModelAttribute(value = "appointmentEdit") AppointmentDto appointmentDto) {
        appointmentServiceImp.updateAppointment(appointmentDto, id);
        return "redirect:/doctor/profile/{id}";
    }


    @PostMapping(value = "/doctor/profile/{id}/pageAppointment", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String cancelAppointment(@PathVariable(name = "id") Long id,HttpServletRequest request) {
        try {
            AppointmentDto appointmentDto = objectMapper.readValue(request.getInputStream(), AppointmentDto.class);
            appointmentServiceImp.cancelAppointment(appointmentDto);
            System.out.println(appointmentDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "0";
    }






}
