package com.t_systems.t_medical_center_system.service;

import com.t_systems.t_medical_center_system.dto.PatientDto;

import java.util.List;

public interface PatientService {

    List<PatientDto> getAllPatients();

    PatientDto getPatientById(Long id);

    void savePatient(PatientDto obj);

    void updatePatient(PatientDto obj);

    void deletePatient(Long id);


}
