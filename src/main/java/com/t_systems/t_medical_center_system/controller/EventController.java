package com.t_systems.t_medical_center_system.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.t_systems.t_medical_center_system.dto.EventDto;
import com.t_systems.t_medical_center_system.dto.Filter;
import com.t_systems.t_medical_center_system.service.EventService;
import com.t_systems.t_medical_center_system.service.impl.EventServiceImp;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.stream.IntStream;

@Controller
public class EventController {

    private EventServiceImp eventService;
    private PatientServiceImp patientServiceImp;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public EventController(EventServiceImp eventServiceImp, PatientServiceImp patientServiceImp) {
        this.eventService = eventServiceImp;
        this.patientServiceImp = patientServiceImp;
    }


    @PostConstruct
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
    }


    @GetMapping("/nurse/eventList")
    public String getEventListFilter(@Param("keyword") String keyword
            , Model model) {

        model.addAttribute("filter", new Filter());
        return "templates/eventList";

    }



    @PostMapping("/nurse/eventList-filter")
    public String getEventListFilterPost(@Param("keyword") String keyword
            , Model model,@ModelAttribute Filter filter) {

        model.addAttribute("events",eventService.doFilter(filter,keyword));
        model.addAttribute("filter",filter);
        return "templates/eventList";

    }


//
    @PostMapping(value = "/nurse/eventList", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeStatusToDone(HttpServletRequest request) {
        try {
            EventDto eventDto = objectMapper.readValue(request.getInputStream(), EventDto.class);
            eventService.updateStatus(eventDto);
            System.out.println(eventDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "0";
    }


}
