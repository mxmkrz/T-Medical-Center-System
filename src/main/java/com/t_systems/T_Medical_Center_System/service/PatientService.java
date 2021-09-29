package com.t_systems.T_Medical_Center_System.service;

import com.t_systems.T_Medical_Center_System.dto.PatientDto;
import com.t_systems.T_Medical_Center_System.entity.Patient;

import java.util.List;

public interface PatientService {

    List<PatientDto> getAllPatients();

    PatientDto getPatientById(Long id);

    void savePatient(PatientDto obj);

    void updatePatient(PatientDto obj);

    void deletePatient(Long id);


}
