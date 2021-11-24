package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.Event;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.entity.enums.AppointmentStatus;
import com.t_systems.t_medical_center_system.entity.enums.PatientStatus;
import com.t_systems.t_medical_center_system.mapper.PatientMapper;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.repository.EventRepository;
import com.t_systems.t_medical_center_system.repository.MedicalStaffRepository;
import com.t_systems.t_medical_center_system.repository.PatientRepository;
import org.junit.Before;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class PatientServiceImpTest {
    @InjectMocks
    private PatientServiceImp patientService;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private PatientMapper modelMapper;
    @Mock
    private MedicalStaffRepository medicalStaffRepository;
    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }



    @Test
    @WithMockUser(roles = "DOCTOR")
    public void save() {
        Patient patient = new Patient();
        PatientDto patientDto = new PatientDto();
        MedicalStaff medicalStaff = new MedicalStaff();
        medicalStaff.setName("Doctor");


        when(modelMapper.toEntity(patientDto)).thenReturn(patient);
        when(medicalStaffRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())).thenReturn(medicalStaff);

        patientService.savePatient(patientDto);

        verify(patientRepository, times(1)).save(patient);
        assertEquals(patient.getDoctorName(), medicalStaff.getName());


    }


    @Test
    void discharge() {
        PatientDto patientDto = new PatientDto();
        patientDto.setId(1L);
        patientDto.setStatus(PatientStatus.DISCHARGED.name());

        Event eventBefore = new Event();
        LocalDate nowMinusFiveYears = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        nowMinusFiveYears = nowMinusFiveYears.minusYears(5);
        Date dateBefore = Date.valueOf(nowMinusFiveYears);
        eventBefore.setDate(dateBefore);
        Event eventAfter = new Event();
        LocalDate eventAfterFiveYears = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        eventAfterFiveYears = eventAfterFiveYears.plusYears(5);
        Date dateAfter = Date.valueOf(eventAfterFiveYears);
        eventAfter.setDate(dateAfter);
        List<Event> events = List.of(eventBefore, eventAfter);


        Patient patient = new Patient();
        patient.setId(1L);


        Appointment appointment = new Appointment();
        appointment.setStatus(AppointmentStatus.ACTIVE);
        appointment.setPatient(patient);
        List<Appointment> appointments = List.of(appointment);

        when(modelMapper.toEntity(patientDto)).thenReturn(patient);
        when(eventRepository.findAllByPatientId(patientDto.getId())).thenReturn(events);
        when(appointmentRepository.findAllByPatientId(patient.getId())).thenReturn(appointments);

        patientService.discharge(patientDto);

        for (Appointment a : appointments) {
            assertEquals(AppointmentStatus.FINISHED, a.getStatus());
        }
        verify(eventRepository, times(1)).delete(any());


    }

}