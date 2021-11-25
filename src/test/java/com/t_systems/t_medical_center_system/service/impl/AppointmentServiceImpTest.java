package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.Event;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.entity.enums.AppointmentStatus;
import com.t_systems.t_medical_center_system.entity.enums.EventStatus;
import com.t_systems.t_medical_center_system.mapper.AppointmentMapper;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.repository.MedicalStaffRepository;
import com.t_systems.t_medical_center_system.repository.PatientRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class AppointmentServiceImpTest {
    @InjectMocks
    private AppointmentServiceImp appointmentServiceImp;
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private AppointmentMapper appointmentMapper;
    @Mock
    private EventServiceImp eventServiceImp;
    @Mock
    private RabbitSender rabbitSender;
    @Mock
    private MedicalStaffRepository medicalStaffRepository;

    @Test
    @WithMockUser(roles = "DOCTOR")
     void makeAnAppointment() {
        AppointmentDto appointmentDto = new AppointmentDto();
        Appointment appointment = new Appointment();
        Patient patient = new Patient();
        patient.setId(1L);
        MedicalStaff medicalStaff = new MedicalStaff();
        medicalStaff.setName("Doctor");

        when(appointmentMapper.toEntity(appointmentDto)).thenReturn(appointment);
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        when(medicalStaffRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())).thenReturn(medicalStaff);

        appointmentServiceImp.makeAnAppointment(appointmentDto, 1L);

        assertNotNull(appointment.getPatient());
        assertNotNull(appointment.getStaff());
        assertEquals(AppointmentStatus.ACTIVE, appointment.getStatus());

        verify(rabbitSender, times(1)).sendMessage(ArgumentMatchers.anyString());
        verify(eventServiceImp, times(1)).generateEvents(appointmentDto, 1L, appointment.getId());
        verify(appointmentRepository, times(1)).save(appointment);

    }

    @Test
    @WithMockUser(roles = "DOCTOR")
     void updateAppointment() {
        AppointmentDto appointmentDto = new AppointmentDto();
        Appointment appointment = new Appointment();
        Patient patient = new Patient();
        patient.setId(1L);
        MedicalStaff medicalStaff = new MedicalStaff();
        medicalStaff.setName("Doctor");

        when(appointmentRepository.findById(appointmentDto.getId())).thenReturn(Optional.of(appointment));
        when(appointmentMapper.toEntity(appointmentDto)).thenReturn(appointment);
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        when(medicalStaffRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())).thenReturn(medicalStaff);

        appointmentServiceImp.updateAppointment(appointmentDto, 1L);

        assertNotNull(appointment.getPatient());
        assertNotNull(appointment.getStaff());

        verify(rabbitSender, times(1)).sendMessage(ArgumentMatchers.anyString());
        verify(eventServiceImp, times(1)).generateEvents(appointmentDto, 1L, appointment.getId());
        verify(appointmentRepository, times(1)).save(appointment);
        verify(eventServiceImp, times(1)).deleteEvent(appointment.getId());

    }

    @Test
     void cancelAppointment() {
        AppointmentDto appointmentDto = new AppointmentDto();
        Appointment appointment = new Appointment();
        appointmentDto.setStatus(AppointmentStatus.FINISHED);
        Event event = new Event();
        Event event1 = new Event();
        Event event2 = new Event();
        Event event3 = new Event();
        List<Event> events = List.of(event, event1, event2, event3);
        for (Event e : events) e.setStatus(EventStatus.PLANNED);


        when(eventServiceImp.findAllByAppointmentId(appointmentDto.getId())).thenReturn(events);
        when(appointmentRepository.findById(appointmentDto.getId())).thenReturn(Optional.of(appointment));

        appointmentServiceImp.cancelOrDoneAppointment(appointmentDto, 1L);

        for (Event e : events) {
            assertEquals(EventStatus.CANCELED, e.getStatus());
            assertNotNull(e.getReasonToCancel());
        }
        assertEquals(AppointmentStatus.FINISHED, appointment.getStatus());

        verify(eventServiceImp, times(4)).updateEvent(ArgumentMatchers.any()
                , ArgumentMatchers.any());
        verify(rabbitSender, times(1)).sendMessage(ArgumentMatchers.anyString());
        verify(appointmentRepository, times(1)).save(appointment);

    }

    @Test
     void doneAppointment() {
        AppointmentDto appointmentDto = new AppointmentDto();
        Appointment appointment = new Appointment();
        appointmentDto.setStatus(AppointmentStatus.DONE);
        Event event = new Event();
        Event event1 = new Event();
        Event event2 = new Event();
        List<Event> events = List.of(event, event1, event2);
        for (Event e : events) e.setStatus(EventStatus.PLANNED);


        when(eventServiceImp.findAllByAppointmentId(appointmentDto.getId())).thenReturn(events);
        when(appointmentRepository.findById(appointmentDto.getId())).thenReturn(Optional.of(appointment));

        appointmentServiceImp.cancelOrDoneAppointment(appointmentDto, 1L);

        for (Event e : events) {
            assertEquals(EventStatus.DONE, e.getStatus());
            assertNotNull(e.getReasonToCancel());
        }
        assertEquals(AppointmentStatus.DONE, appointment.getStatus());

        verify(eventServiceImp, times(3)).updateEvent(ArgumentMatchers.any()
                , ArgumentMatchers.any());
        verify(rabbitSender, times(1)).sendMessage(ArgumentMatchers.anyString());
        verify(appointmentRepository, times(1)).save(appointment);

    }


}