package com.t_systems.t_medical_center_system.service;

import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.Patient;

import java.util.List;

public interface PatientService {
    List<PatientDto> getAllPatients();
    PatientDto getPatientById(Long id);
    void savePatient(PatientDto patient);
    void updatePatient(PatientDto obj);
    void deletePatient(Long id);




}
