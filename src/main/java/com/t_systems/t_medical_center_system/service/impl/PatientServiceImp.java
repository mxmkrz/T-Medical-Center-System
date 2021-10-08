package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.converter.Convertor;
import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.entity.enums.PatientStatus;
import com.t_systems.t_medical_center_system.exception.PatientNotFoundException;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.repository.PatientRepository;

import com.t_systems.t_medical_center_system.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
public class PatientServiceImp implements PatientService {

    private final PatientRepository patientRepository;
    private final Convertor<Patient, PatientDto> patientConvertor;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AppointmentRepository appointmentRepository;


    @Autowired
    public PatientServiceImp(PatientRepository patientRepository, Convertor<Patient, PatientDto> patientConvertor, BCryptPasswordEncoder bCryptPasswordEncoder, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.patientConvertor = patientConvertor;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.appointmentRepository = appointmentRepository;
    }


    @Transactional(readOnly = true)
    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> result = (List<Patient>) patientRepository.findAll();
        return patientConvertor.convertLisToDto(result, PatientDto.class);
    }




    @Transactional(readOnly = true)
    @Override
    public PatientDto getPatientById(Long id) {
        return patientConvertor.convertToDto(patientRepository.findById(id).orElseThrow(PatientNotFoundException::new),PatientDto.class);
    }

    @Transactional
    @Override
    public void savePatient(Patient patient) {
        patient.setPatientStatus(PatientStatus.PATIENT);
        patientRepository.save(patient);
        log.info("Add patient");
    }

    @Transactional
    @Override
    public void updatePatient(PatientDto obj) {
        patientRepository.save(patientConvertor.convertToEntity(obj, Patient.class));
    }


    @Transactional
    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Appointment> getAppointment(Long id) {
        return null;
    }


}
