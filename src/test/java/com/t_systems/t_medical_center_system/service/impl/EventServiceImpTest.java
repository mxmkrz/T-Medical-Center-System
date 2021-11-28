package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.dto.EventBoardDto;
import com.t_systems.t_medical_center_system.dto.EventDto;
import com.t_systems.t_medical_center_system.dto.Filter;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.Event;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.entity.enums.EventStatus;
import com.t_systems.t_medical_center_system.entity.enums.TherapyType;
import com.t_systems.t_medical_center_system.mapper.EventMapper;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.repository.EventRepository;
import com.t_systems.t_medical_center_system.repository.PatientRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class EventServiceImpTest {
    @InjectMocks
    private EventServiceImp eventServiceImp;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private EventMapper eventMapper;
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private RabbitSender rabbitSender;



    @Test
     void findAllEventsDayForBoard() {
        LocalDate localDate = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        localDate = localDate.plusMonths(1).minusDays(1);
        Date dateBefore = Date.valueOf(localDate);
        LocalDate plusDay = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        plusDay = plusDay.plusMonths(1).plusDays(1);
        Date dateAfter = Date.valueOf(plusDay);

        LocalDate now = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        now = now.plusMonths(1);
        Date date = Date.valueOf(now);

        LocalDate date1 = LocalDate.of(2018, 11, 5);
        Date date2 = Date.valueOf(date1);
        LocalDate date3 = LocalDate.of(2016, 11, 5);
        Date date4 = Date.valueOf(date3);
        LocalDate date5 = LocalDate.of(2014, 11, 5);
        Date date6 = Date.valueOf(date5);

        Event event = new Event();
        event.setDate(date);
        Event event1 = new Event();
        event1.setDate(date2);
        Event event2 = new Event();
        event2.setDate(date4);
        Event event3 = new Event();
        event3.setDate(date6);

        List<Event> events = List.of(event, event1, event2, event3);

        when(eventRepository.findAllForDay(dateAfter,dateBefore)).thenReturn(events);

        eventServiceImp.findAllEventsDayForBoard();
        verify(eventMapper).toStringDtoList(events);





    }

    @Test
     void getDataBetweenStartEndData() {
        LocalDate nowMinusFiveDay = LocalDate.of(2020, 11, 5);
        Date dateBefore = Date.valueOf(nowMinusFiveDay);
        LocalDate nowPlusFiveDay = LocalDate.of(2020, 11, 15);
        Date dateAfter = Date.valueOf(nowPlusFiveDay);
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setStartOfData(dateBefore);
        appointmentDto.setEndOfData(dateAfter);
        List<Date> dateList = eventServiceImp.getDataBetweenStartEndData(appointmentDto);
        assertEquals(11, dateList.size());
    }


    @Test
     void countDataAndTime() {
        AppointmentDto appointmentDto = new AppointmentDto();
        LocalDate nowMinusFiveDay = LocalDate.of(2021, 10, 5);
        Date dateBefore = Date.valueOf(nowMinusFiveDay);
        LocalDate nowPlusFiveDay = LocalDate.of(2021, 10, 15);
        Date dateAfter = Date.valueOf(nowPlusFiveDay);
        appointmentDto.setStartOfData(dateBefore);
        appointmentDto.setEndOfData(dateAfter);
        appointmentDto.setTime(new ArrayList<>(List.of("0","1","2", "3","4","5","6","7","8")));
        appointmentDto.setTuesday(true);
        appointmentDto.setMonday(true);
        appointmentDto.setFriday(true);
        appointmentDto.setSaturday(true);

        Map<Date, List<LocalTime>> map = eventServiceImp.countDataAndTime(appointmentDto);
        assertEquals(6, map.size());


    }

    @Test
     void generateEvents() {
        AppointmentDto appointmentDto = new AppointmentDto();
        LocalDate nowMinusFiveDay = LocalDate.of(2021, 10, 6);
        Date dateBefore = Date.valueOf(nowMinusFiveDay);
        LocalDate nowPlusFiveDay = LocalDate.of(2021, 10, 15);
        Date dateAfter = Date.valueOf(nowPlusFiveDay);
        appointmentDto.setStartOfData(dateBefore);
        appointmentDto.setEndOfData(dateAfter);
        appointmentDto.setTime(new ArrayList<>(List.of("2", "3")));
        appointmentDto.setTuesday(true);
        appointmentDto.setType(TherapyType.PROCEDURE);
        Appointment appointment = new Appointment();
        Patient patient = new Patient();

        when(appointmentRepository.findById(3L)).thenReturn(Optional.of(appointment));
        when(patientRepository.findById(2L)).thenReturn(Optional.of(patient));

        eventServiceImp.generateEvents(appointmentDto, 2L, 3L);

        verify(eventRepository, times(2)).save(any());


    }


    @Test
     void updateStatusCancel() {
        EventDto eventDto = new EventDto();
        Event event = new Event();
        Patient patient = new Patient();
        Appointment appointment = new Appointment();
        eventDto.setStatus(EventStatus.CANCELED);

        when(eventMapper.toEntity(eventDto)).thenReturn(event);
        when(patientRepository.findById(eventDto.getIdPatient())).thenReturn(Optional.of(patient));
        when(eventRepository.findById(eventDto.getId())).thenReturn(Optional.of(event));
        when(appointmentRepository.findById(eventDto.getIdAppointment())).thenReturn(Optional.of(appointment));

        eventServiceImp.updateStatus(eventDto);

        verify(rabbitSender, times(1)).sendMessage(ArgumentMatchers.anyString());
        verify(eventRepository, times(1)).save(event);
        assertEquals(EventStatus.CANCELED, event.getStatus());
        assertEquals(event.getReasonToCancel(), eventDto.getReasonToCancel());

    }

    @Test
     void updateStatusDone() {
        EventDto eventDto = new EventDto();
        Event event = new Event();
        Patient patient = new Patient();
        Appointment appointment = new Appointment();
        eventDto.setStatus(EventStatus.DONE);

        when(eventMapper.toEntity(eventDto)).thenReturn(event);
        when(patientRepository.findById(eventDto.getIdPatient())).thenReturn(Optional.of(patient));
        when(eventRepository.findById(eventDto.getId())).thenReturn(Optional.of(event));
        when(appointmentRepository.findById(eventDto.getIdAppointment())).thenReturn(Optional.of(appointment));

        eventServiceImp.updateStatus(eventDto);

        verify(rabbitSender, times(1)).sendMessage(ArgumentMatchers.anyString());
        verify(eventRepository, times(1)).save(event);
        assertEquals(EventStatus.DONE, event.getStatus());
        assertNull(eventDto.getReasonToCancel());

    }

    @Test
    void updateEvent() {
        Event event = new Event();
        event.setId(26L);
        Patient patient = new Patient();
        patient.setId(3L);
        Appointment appointment = new Appointment();
        event.setPatient(patient);
        event.setAppointment(appointment);

        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        when(eventRepository.findById(event.getId())).thenReturn(Optional.of(event));
        when(appointmentRepository.findById(14L)).thenReturn(Optional.of(appointment));

        eventServiceImp.updateEvent(event,14L);
        verify(eventRepository, times(1)).save(event);
        assertEquals(patient, event.getPatient());
        assertEquals(appointment,event.getAppointment());


    }


}