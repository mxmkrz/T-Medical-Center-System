package com.t_systems.t_medical_center_system.controller;


import com.t_systems.t_medical_center_system.dto.EventDto;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.service.impl.EventServiceImp;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EventController {

    private EventServiceImp eventServiceImp;
    private PatientServiceImp patientServiceImp;

    @Autowired
    public EventController(EventServiceImp eventServiceImp, PatientServiceImp patientServiceImp) {
        this.eventServiceImp = eventServiceImp;
        this.patientServiceImp = patientServiceImp;
    }


    @GetMapping("/nurse/eventList")
    public String getEventListFilter(@Param("keyword") String keyword
            , Model model) {


        model.addAttribute("events", eventServiceImp.findAllEvents());
        model.addAttribute("patients", patientServiceImp.getAllPatients());
        model.addAttribute("eventsForDay", eventServiceImp.findAllEventsForDay());
        model.addAttribute("eventsForHour", eventServiceImp.findAllEventsForHour());
        model.addAttribute("eventFilterPatient", eventServiceImp.findAllPatientByName(keyword));
//        model.addAttribute("event",eventServiceImp.getById())


        return "templates/eventList";

    }


    @PostMapping(value = "/nurse/eventList", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeStatus(@ModelAttribute("events") EventDto eventDto) {
        eventServiceImp.updateEventStatus(eventDto);
        return "redirect:/nurse/eventList";
    }




//
//
//    @GetMapping("/nurse/eventList/{id}")
//    public String doCancelGet(@PathVariable(name = "id") Long id, Model model) {
//        eventServiceImp.updateEventStatus(EventStatus.CANCELED, id);
//        return "templates/eventPage";
//
//    }










//    @GetMapping("/{eventId}/changeToCancelled")
//    public ModelAndView doCance(@PathVariable("eventId") Long eventId){
//        LOGGER.info("Changing event status to CANCEL for event with id = {}", eventId);
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("event",eventService.getOne(eventId));
//        modelAndView.setViewName("event/why_cancelled");
//        eventService.changeStatus(eventId, EventStatus.CANCELED);
//        return modelAndView;
//    }
//
//    @PostMapping("/nurse/eventList/{id}/changeToCancel")
//    public String doCancelPost(@ModelAttribute EventPO eventPO,
//                           @PathVariable("eventId") Long eventId){
//        LOGGER.info("Setting reason to cancel event with id = {}", eventId);
//
//        eventService.setReasonToCancel(eventPO.getReasonToCancel(), eventId);
//        eventScheduleService.updateSchedule();
//        return "redirect:/event/";
//    }


}
