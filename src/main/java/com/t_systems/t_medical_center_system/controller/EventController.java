package com.t_systems.t_medical_center_system.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.t_systems.t_medical_center_system.dto.EventDto;
import com.t_systems.t_medical_center_system.service.impl.EventServiceImp;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Controller
public class EventController {

    private EventServiceImp eventServiceImp;
    private PatientServiceImp patientServiceImp;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public EventController(EventServiceImp eventServiceImp, PatientServiceImp patientServiceImp) {
        this.eventServiceImp = eventServiceImp;
        this.patientServiceImp = patientServiceImp;
    }



    @PostConstruct
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
    }


    @GetMapping("/nurse/eventList")
    public String getEventListFilter(@Param("keyword") String keyword
            , Model model) {

        model.addAttribute("events", eventServiceImp.findAllEvents());
        model.addAttribute("patients", patientServiceImp.getAllPatients());
        model.addAttribute("eventsForDay", eventServiceImp.findAllEventsForDay());
        model.addAttribute("eventsForHour", eventServiceImp.findAllEventsForHour());
        model.addAttribute("eventFilterPatient", eventServiceImp.findAllPatientByName(keyword));
        return "templates/eventList";

    }

    @PostMapping(value = "/nurse/eventList", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeStatusToDone(HttpServletRequest request) {
        try {
            EventDto eventDto = objectMapper.readValue(request.getInputStream(), EventDto.class);
            eventServiceImp.updateStatusToDone(eventDto);
            System.out.println(eventDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "0";
    }



}
