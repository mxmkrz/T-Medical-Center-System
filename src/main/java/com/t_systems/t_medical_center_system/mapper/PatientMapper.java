package com.t_systems.t_medical_center_system.mapper;

import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.entity.enums.PatientStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class PatientMapper {


    public Patient toEntity(PatientDto patientDto){
        Patient patient = new Patient();
        patient.setId(patientDto.getId());
        patient.setName(patientDto.getName());
        patient.setSurname(patientDto.getSurname());
        patient.setDiagnosis(patientDto.getDiagnosis());
        patient.setInsuranceNumber(patientDto.getInsuranceNumber());
        if (patientDto.getStatus().equals(PatientStatus.PATIENT.name())) patient.setPatientStatus(PatientStatus.PATIENT);
        if (patientDto.getStatus().equals(PatientStatus.DISCHARGED.name())) patient.setPatientStatus(PatientStatus.DISCHARGED);
       return patient;
    }

    public PatientDto toDto(Patient patient){
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setName(patient.getName());
        patientDto.setSurname(patient.getSurname());
        patientDto.setDoctorsName(patient.getDoctorName());
        patientDto.setInsuranceNumber(patient.getInsuranceNumber());
        patientDto.setDiagnosis(patient.getDiagnosis());
        patientDto.setStatus(patient.getPatientStatus().name());
        return patientDto;
    }
}
