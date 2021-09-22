package com.t_systems.T_Medical_Center_System.service;

import com.t_systems.T_Medical_Center_System.converter.PatientConvertor;
import com.t_systems.T_Medical_Center_System.dto.PatientDto;
import com.t_systems.T_Medical_Center_System.entity.Patient;
import com.t_systems.T_Medical_Center_System.exception.PatientNotFoundException;
import com.t_systems.T_Medical_Center_System.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService implements HelperService<PatientDto> {

    private final PatientRepository patientRepository;
    private final PatientConvertor patientConvertor;

    public PatientService(PatientRepository patientRepository, PatientConvertor patientConvertor) {
        this.patientRepository = patientRepository;
        this.patientConvertor = patientConvertor;
    }

    @Override
    public List<PatientDto> findAll() {
        List<PatientDto> patientsDto = new ArrayList<>();
        Iterable<Patient> patientsIter = patientRepository.findAll();
        List<Patient> patients = new ArrayList<>();
        for (Patient p : patientsIter) {
            patients.add(p);
        }
        for (Patient p : patients) {
            PatientDto patientDTO = patientConvertor.convertToDto(p);
            patientsDto.add(patientDTO);
        }
        return patientsDto;
    }

    @Override
    public PatientDto findById(Long id) {
        return patientConvertor.convertToDto(patientRepository.findById(id).orElseThrow(PatientNotFoundException::new));
    }

    @Override
    public void save(PatientDto obj) {
        patientRepository.save(patientConvertor.convertToEntity(obj));
    }

    @Override
    public void update(PatientDto obj) {
        patientConvertor.convertToDto(patientRepository.findById(obj.getId()).orElseThrow(PatientNotFoundException::new));
        patientRepository.save(patientConvertor.convertToEntity(obj));
    }

    @Override
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }


}
