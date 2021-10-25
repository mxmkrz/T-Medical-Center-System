package com.t_systems.t_medical_center_system.service;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.dto.EventDto;
import com.t_systems.t_medical_center_system.entity.Event;


import java.util.List;

public interface EventService {
    void saveEvent(Event event);
    void deleteEvent(Long id);
    List<Event> findAllByAppointmentId(Long id);
    void generateEvents(AppointmentDto appointmentDto, Long idPatient, Long appointmentId);
    List<EventDto> findAllEvents();
    List<EventDto> findAllEventsForHour();
    List<EventDto> findAllEventsForDay();
    List<EventDto> findAllPatientByName(String name);
    void updateStatus(EventDto eventDto);
    void updateEvent(Event event, Long idAppointment);



}
