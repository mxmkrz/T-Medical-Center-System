package com.t_systems.T_Medical_Center_System.service;

import com.t_systems.T_Medical_Center_System.component.PatientConvector;
import com.t_systems.T_Medical_Center_System.dto.PatientDto;
import com.t_systems.T_Medical_Center_System.repository.PatientRepository;

import org.springframework.stereotype.Service;

@Service
public class PatientService {

    

    private PatientConvector patientConvector;
    private PatientRepository patientRepository;

    public PatientDto getPatientById(Long id) {
        return patientConvector.convertToDto(patientRepository.getPatientBy(id));
    }

}
