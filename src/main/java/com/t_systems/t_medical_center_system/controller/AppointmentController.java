package com.t_systems.t_medical_center_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.service.impl.AppointmentServiceImp;
import com.t_systems.t_medical_center_system.service.impl.MailServiceImp;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
import com.t_systems.t_medical_center_system.util.AppointmentValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
public class AppointmentController {
    private final AppointmentServiceImp appointmentServiceImp;
    private final PatientServiceImp patientServiceImp;
    private final ObjectMapper objectMapper;
    private final AppointmentValidator appointmentValidator;
    private final MailServiceImp mailServiceImp;

    @Autowired
    public AppointmentController(AppointmentServiceImp appointmentServiceImp, PatientServiceImp patientServiceImp, ObjectMapper objectMapper, AppointmentValidator appointmentValidator, MailServiceImp mailServiceImp) {
        this.appointmentServiceImp = appointmentServiceImp;
        this.patientServiceImp = patientServiceImp;
        this.objectMapper = objectMapper;
        this.appointmentValidator = appointmentValidator;
        this.mailServiceImp = mailServiceImp;
    }


    @PostConstruct
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    //*******************************************
    @GetMapping(value = "/doctor/profile/{idPatient}/appointment")
    public String newAppointment(@PathVariable(name = "idPatient") Long id,
                                 Model model) {
        model.addAttribute("patient", patientServiceImp.getPatientById(id));
        model.addAttribute("appointmentNew", new AppointmentDto());
        return "templates/appointment";
    }

    @PostMapping(value = "/doctor/profile/{idPatient}/appointment")
    public String newAppointment(@ModelAttribute(value = "appointmentNew") @Valid AppointmentDto appointmentDto
            , BindingResult bindingResult
            , @PathVariable(name = "idPatient") Long id
            , Model model) {
        model.addAttribute("patient", patientServiceImp.getPatientById(id));
        appointmentValidator.validate(appointmentDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "templates/appointment";
        }
        appointmentServiceImp.makeAnAppointment(appointmentDto, id);

        return "redirect:/doctor/profile/{idPatient}";

    }

    //*******************************************
    @GetMapping(value = "/doctor/profile/{id}/appointments")
    public String getAppointments(@PathVariable(name = "id") Long id
            , Model model
            , @PageableDefault(size = 6, sort = {"startDate"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<AppointmentDto> pages = appointmentServiceImp.getAppointmentListByPatientId(id, pageable);
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalElements", pages.getTotalElements());
        model.addAttribute("size", pages.getSize());
        model.addAttribute("pageAppointment", pages.getContent());
        model.addAttribute("patient", patientServiceImp.getPatientById(id));
        return "templates/appointments";
    }


    //*******************************************
    @GetMapping(value = "/doctor/profile/{id}/edit/{idAppointment}")
    public String editAppointment(@PathVariable(name = "id") Long id,
                                  @PathVariable(name = "idAppointment") Long idAppointment, Model model) {
        model.addAttribute("patient", patientServiceImp.getPatientById(id));
        model.addAttribute("editAppointment", appointmentServiceImp.getAppointById(idAppointment));
        return "templates/appointmentEdit";
    }


    @PostMapping(value = "/doctor/profile/{id}/edit/{idAppointment}")
    public String editAppointment(@PathVariable(name = "id") Long id
            , @PathVariable(name = "idAppointment") Long idAppointment
            , @ModelAttribute(value = "editAppointment") @Valid AppointmentDto appointmentDto
            , BindingResult bindingResult
            , Model model) {
        model.addAttribute("patient", patientServiceImp.getPatientById(id));
        appointmentValidator.validate(appointmentDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "templates/appointmentEdit";
        }
        appointmentServiceImp.updateAppointment(appointmentDto, id);
        return "redirect:/doctor/profile/{id}/appointments";
    }

    //*******************************************
    @PostMapping(value = "/doctor/profile/{id}/pageAppointment", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String cancelOrDoneAppointment(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        try {
            AppointmentDto appointmentDto = objectMapper.readValue(request.getInputStream(), AppointmentDto.class);
            appointmentServiceImp.cancelOrDoneAppointment(appointmentDto, id);
        } catch (Exception e) {
           log.warn(e.getMessage());
        }
        return "0";
    }

//    *******************************************
    @PostMapping(value = "/doctor/profile/{id}/email", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String sendAppointmentAnEmail(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        try {
            AppointmentDto appointmentDto = objectMapper.readValue(request.getInputStream(), AppointmentDto.class);
            mailServiceImp.sendSimpleMessage(appointmentDto,id);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return "0";
    }
}
