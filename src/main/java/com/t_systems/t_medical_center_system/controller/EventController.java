package com.t_systems.t_medical_center_system.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.t_systems.t_medical_center_system.dto.EventDto;
import com.t_systems.t_medical_center_system.dto.Filter;
import com.t_systems.t_medical_center_system.service.impl.EventServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

@Controller
@Slf4j
public class EventController {

    private final EventServiceImp eventService;
    private final ObjectMapper objectMapper;

    @Autowired
    public EventController(EventServiceImp eventService, ObjectMapper objectMapper) {
        this.eventService = eventService;
        this.objectMapper = objectMapper;
    }




    @PostConstruct
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @GetMapping("/nurse/events")
    public String getEventListFilter(Model model, @Param("keyword") String keyword,
                                     @Param(value = "filter") Filter filter,
                                     @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {

        if (filter.getAnInt() != null) {
            Page<EventDto> pages = eventService.doFilter(filter, keyword, pageable);

            model.addAttribute("filter", filter);
            model.addAttribute("keyword", keyword);
            model.addAttribute("number", pages.getNumber());
            model.addAttribute("totalPages", pages.getTotalPages());
            model.addAttribute("totalElements", pages.getTotalElements());
            model.addAttribute("size", pages.getSize());
            model.addAttribute("data", pages.getContent());
        }
        return "templates/events";
    }

    @PostMapping(value = "/nurse/events", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeStatus(HttpServletRequest request) {
        try {
            EventDto eventDto = objectMapper.readValue(request.getInputStream(), EventDto.class);
            eventService.updateStatus(eventDto);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return "0";
    }


}
