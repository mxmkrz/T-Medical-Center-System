package com.t_systems.T_Medical_Center_System.service;

import com.t_systems.T_Medical_Center_System.converter.Convertor;
import com.t_systems.T_Medical_Center_System.dto.PatientDto;
import com.t_systems.T_Medical_Center_System.entity.Patient;
import com.t_systems.T_Medical_Center_System.exception.PatientNotFoundException;
import com.t_systems.T_Medical_Center_System.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService implements ServiceImpl<PatientDto> {

    private final PatientRepository patientRepository;
    private final Convertor<Patient, PatientDto> patientConvertor;

    @Autowired
    public PatientService(@NonNull PatientRepository patientRepository, @NonNull Convertor<Patient, PatientDto> patientConvertor) {
        this.patientRepository = patientRepository;
        this.patientConvertor = patientConvertor;


    }

    @Transactional
    @Override
    public List<PatientDto> findAll() {
        List<Patient> result = patientRepository.findAllList();
        return patientConvertor.convertListEntityToDto(result, PatientDto.class);

    }

    @Transactional
    @Override
    public PatientDto findById(Long id) {
        return patientConvertor.convertToDto(patientRepository.findById(id).orElseThrow(PatientNotFoundException::new), PatientDto.class);
    }

    @Transactional
    @Override
    public void save(PatientDto obj) {
        patientRepository.save(patientConvertor.convertToEntity(obj, Patient.class));
    }

    @Transactional
    @Override
    public void update(PatientDto obj) {
    }


    @Transactional
    @Override
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }
    @Transactional
    public void updatePatientByStatus(PatientDto obj, Boolean status) {
        Patient patient = patientConvertor.convertToEntity(obj, Patient.class);
        Patient patient1 = patientRepository.findById(patient.getId()).orElseThrow(PatientNotFoundException::new);
        patient1.setStatus(status);
        patientRepository.save(patient1);
    }


}
