package com.t_systems.t_medical_center_system.repository;

import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.entity.Patient;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {
    MedicalStaff findByName(String name);


}
