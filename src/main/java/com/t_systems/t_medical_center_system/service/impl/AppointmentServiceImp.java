package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.mapper.Convertor;
import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.repository.PatientRepository;
import com.t_systems.t_medical_center_system.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImp implements AppointmentService {

    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;
    private Convertor<Appointment,AppointmentDto> convertor;

    @Autowired
    public AppointmentServiceImp(AppointmentRepository appointmentRepository, PatientRepository patientRepository, Convertor<Appointment, AppointmentDto> convertor) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.convertor = convertor;
    }


    @Override
    public List<AppointmentDto> getAllAppointments() {
        List<Appointment> result = (List<Appointment>) appointmentRepository.findAll();
        return convertor.convertLisToDto(result, AppointmentDto.class);

    }

    @Override
    public void addAppointment(List<AppointmentDto> appointment,Long id) {

    }


}
