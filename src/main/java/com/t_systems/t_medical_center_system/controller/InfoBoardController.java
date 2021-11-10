package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.dto.EventBoardDto;
import com.t_systems.t_medical_center_system.service.impl.EventServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/infoboard")
public class InfoBoardController {


    private final EventServiceImp eventServiceImp;

    @Autowired
    public InfoBoardController(EventServiceImp eventServiceImp) {
        this.eventServiceImp = eventServiceImp;
    }


    @GetMapping(value = "/occasion")
    public List<EventBoardDto> getEventStringsDto() {
        return eventServiceImp.findAllEventsDayForBoard();
    }
}
