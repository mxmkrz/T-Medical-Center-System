package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.repository.MedicalStaffRepository;
import com.t_systems.t_medical_center_system.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//class MailServiceImpTest {
//    @InjectMocks
//    private MailServiceImp mailServiceImp;
//    @Mock
//    private PatientRepository patientRepository;
//    @Mock
//    private  MedicalStaffRepository medicalStaffRepository;
//    @Mock
//    private  AppointmentRepository appointmentRepository;
//    @Mock
//    private  SimpleMailMessage mailMessage;
//
//
//    @Test
//    public void sendSimpleMessage() {
////        AppointmentDto appointmentDto = new AppointmentDto();
////        Appointment appointment = new Appointment();
////        appointmentDto.setId(2L);
////        Patient patient= new Patient();
////        patient.setDoctorName("Doctor");
////        MedicalStaff medicalStaff = new MedicalStaff();
////        medicalStaff.setEmail("email@mail.ru");
////        mailMessage.setTo(medicalStaff.getEmail());
////        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
////
////        when(appointmentRepository.findById(appointmentDto.getId())).thenReturn(Optional.of(appointment));
////        when(medicalStaffRepository.findByName(patient.getDoctorName())).thenReturn(medicalStaff);
////
////
////        mailServiceImp.sendSimpleMessage(appointmentDto,1L);
////
////        verify(mailMessage).setTo(ArgumentMatchers.anyString());
//
//
//
//
//    }
//
//}