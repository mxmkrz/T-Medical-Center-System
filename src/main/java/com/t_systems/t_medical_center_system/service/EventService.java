package com.t_systems.t_medical_center_system.service;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.dto.EventBoardDto;
import com.t_systems.t_medical_center_system.dto.EventDto;
import com.t_systems.t_medical_center_system.dto.Filter;
import com.t_systems.t_medical_center_system.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface EventService {
    void saveEvent(Event event);

    void deleteEvent(Long id);

    List<Event> findAllByAppointmentId(Long id);

    void generateEvents(AppointmentDto appointmentDto, Long idPatient, Long appointmentId);

    List<EventBoardDto> findAllEventsDayForBoard();

    void updateStatus(EventDto eventDto);

    void updateEvent(Event event, Long idAppointment);

    Page<EventDto> findAllEvents(Pageable pageable);

    Page<EventDto> findAllEventsForHour(Pageable pageable);

    Page<EventDto> findAllEventsForDay(Pageable pageable);

    Page<EventDto> findAllPatientByName(String name, Pageable pageable);

    Page<EventDto> doFilter(Filter filter, String keyword, Pageable pageable);


}
