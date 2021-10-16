package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.exception.AppointmentNotFoundException;
import com.t_systems.t_medical_center_system.exception.PatientNotFoundException;
import com.t_systems.t_medical_center_system.mapper.AppointmentMapper;
import com.t_systems.t_medical_center_system.mapper.Convertor;
import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.repository.MedicalStaffRepository;
import com.t_systems.t_medical_center_system.repository.PatientRepository;
import com.t_systems.t_medical_center_system.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImp implements AppointmentService {

    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;
    private Convertor<Appointment, AppointmentDto> convertor;
    private AppointmentMapper appointmentMapper;
    private MedicalStaffRepository medicalStaffRepository;


    @Autowired
    public AppointmentServiceImp(AppointmentRepository appointmentRepository, PatientRepository patientRepository, Convertor<Appointment, AppointmentDto> convertor, AppointmentMapper appointmentMapper, MedicalStaffRepository medicalStaffRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.convertor = convertor;
        this.appointmentMapper = appointmentMapper;
        this.medicalStaffRepository = medicalStaffRepository;

    }


    @Override
    public void addAppointment(AppointmentDto appointment, Long id) {
        Appointment appointmentEntity = appointmentMapper.toEntity(appointment);
        Patient patient = patientRepository.findById(id).orElseThrow(PatientNotFoundException::new);
        appointmentEntity.setPatient(patient);
        appointmentRepository.save(appointmentEntity);
    }


    @Override
    public List<AppointmentDto> getAppointmentListByPatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(PatientNotFoundException::new);
        List<Appointment> appointments = appointmentRepository.findAllByPatient(patient);
        return appointmentMapper.toDtoList(appointments);
    }

    @Override
    public AppointmentDto gitAppointById(Long id) {

        Appointment appointment = appointmentRepository.findById(id).orElseThrow(AppointmentNotFoundException::new);
        return appointmentMapper.toDto(appointment);
    }

    @Override
    public void updateAppointment(AppointmentDto appointmentDto,Long idPatient) {
        Appointment appointmentEntity = appointmentRepository.findById(appointmentDto.getId()).orElseThrow(AppointmentNotFoundException::new);
        appointmentEntity = appointmentMapper.toEntity(appointmentDto);
        Patient patient = patientRepository.findById(idPatient).orElseThrow(PatientNotFoundException::new);
        appointmentEntity.setPatient(patient);
        appointmentRepository.save(appointmentEntity);
    }


}
