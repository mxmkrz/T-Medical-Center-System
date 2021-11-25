package com.t_systems.t_medical_center_system.service;

import com.t_systems.t_medical_center_system.dto.PatientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {
    Page<PatientDto> getAllPatients(Pageable pageable);

    PatientDto getPatientById(Long id);

    void savePatient(PatientDto patient);

    void updatePatient(PatientDto obj);

    void deletePatient(Long id);

    void discharge(PatientDto patientDto);


}
