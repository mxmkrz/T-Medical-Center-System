package com.t_systems.t_medical_center_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.service.impl.AppointmentServiceImp;
import com.t_systems.t_medical_center_system.service.impl.EventServiceImp;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

//
//
//
    @ExceptionHandler
    public String handleException(ConversionFailedException e){
        return e.getMessage();
    }


    @GetMapping(value = "/doctor/profile/{idPatient}/appointment")
    public String newAppointmentGet(@PathVariable(name = "idPatient") Long id,
                                    Model model) {
        model.addAttribute("patient", patientServiceImp.getPatientById(id));
        model.addAttribute("appointmentNew", new AppointmentDto());
        return "templates/appointment";
    }


    @PostMapping(value = "/doctor/profile/{idPatient}/appointment")
    public String newAppointmentPost(@PathVariable(name = "idPatient") Long id,
                                     @ModelAttribute(value = "appointmentNew") @Valid AppointmentDto appointmentDto, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()){
            return "redirect:/doctor/profile/{idPatient}/appointment";

        }
        appointmentServiceImp.makeAnAppointment(appointmentDto, id);
        return "redirect:/doctor/profile/{idPatient}";
    }



    @GetMapping(value = "/doctor/profile/{id}/pageAppointment")
    public String getAppointmentPage(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("pageAppointment", appointmentServiceImp.getAppointmentListByPatientId(id));
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
                                      @PathVariable(name = "idAppointment") Long idAppointment,
                                      @ModelAttribute(value = "appointmentEdit") @Valid AppointmentDto appointmentDto,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "redirect:/doctor/profile/{id}/edit/{idAppointment}";

        }

        appointmentServiceImp.updateAppointment(appointmentDto, id);
        return "redirect:/doctor/profile/{id}/pageAppointment";
    }


    @PostMapping(value = "/doctor/profile/{id}/pageAppointment", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String cancelAppointment(@PathVariable(name = "id") Long id,HttpServletRequest request) {
        try {
            AppointmentDto appointmentDto = objectMapper.readValue(request.getInputStream(), AppointmentDto.class);
            appointmentServiceImp.cancelAppointment(appointmentDto,id);
            System.out.println(appointmentDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "0";
    }






}
