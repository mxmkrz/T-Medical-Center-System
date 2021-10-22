package com.t_systems.t_medical_center_system.mapper;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.dto.EventDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.Event;
import com.t_systems.t_medical_center_system.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class EventMapper {

    private EventRepository eventRepository;

    @Autowired
    public EventMapper(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventDto toDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setPatient(event.getPatient());
        eventDto.setEventDateTime(event.getDate());
        eventDto.setTherapyType(event.getTherapyType());
        eventDto.setTime(event.getTime());
        eventDto.setStatus(event.getStatus());
        eventDto.setReasonToCancel(event.getReasonToCancel());
        eventDto.setAppointment(event.getAppointment());
        return eventDto;
    }



    public List<EventDto> toDtoList(List<Event> events) {
        List<EventDto> eventDtos = new ArrayList<>();
        for (Event e : events) {
            eventDtos.add(toDto(e));
        }
        return eventDtos;
    }


    public Event toEntity(EventDto eventDto){
        Event event = new Event();
        event.setId(eventDto.getId());
        event.setDate(eventDto.getEventDateTime());
        event.setTime(eventDto.getTime());

        event.setTherapyType(eventDto.getTherapyType());
        event.setReasonToCancel(event.getReasonToCancel());
        return  event;
    }
}
