package com.t_systems.T_Medical_Center_System.service.impl;

import com.t_systems.T_Medical_Center_System.converter.Convertor;
import com.t_systems.T_Medical_Center_System.dto.PatientDto;
import com.t_systems.T_Medical_Center_System.entity.Patient;
import com.t_systems.T_Medical_Center_System.exception.PatientNotFoundException;
import com.t_systems.T_Medical_Center_System.repository.PatientRepository;

import com.t_systems.T_Medical_Center_System.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
public class PatientServiceImp implements PatientService {

    private final PatientRepository patientRepository;
    private final Convertor<Patient, PatientDto> patientConvertor;

    @Autowired
    public PatientServiceImp(@NonNull PatientRepository patientRepository, @NonNull Convertor<Patient, PatientDto> patientConvertor) {
        this.patientRepository = patientRepository;
        this.patientConvertor = patientConvertor;
    }

    @Transactional
    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> result = patientRepository.findAllList();
        return patientConvertor.convertLisToDto(result, PatientDto.class);
    }

    @Transactional
    @Override
    public PatientDto getPatientById(Long id) {
        return patientConvertor.convertToDto(patientRepository.findById(id).orElseThrow(PatientNotFoundException::new),PatientDto.class);
    }

    @Transactional
    @Override
    public void savePatient(PatientDto obj) {
        patientRepository.save(patientConvertor.convertToEntity(obj, Patient.class));
    }

    @Transactional
    @Override
    public void updatePatient(PatientDto obj) {
        Patient patient = patientRepository.findById(obj.getId()).orElseThrow(PatientNotFoundException::new);
        patient.setFirstName(obj.getFirstName());
        patient.setSecondName(obj.getSecondName());
        patient.setDiagnosis(obj.getDiagnosis());
        patient.setDoctorsName(obj.getDoctorsName());
        patient.setInsuranceNumber(obj.getInsuranceNumber());
        patient.setStatus(obj.getStatus());
    }


    @Transactional
    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }



}
