package com.t_systems.t_medical_center_system.repository;

import com.t_systems.t_medical_center_system.entity.Doctor;
import com.t_systems.t_medical_center_system.entity.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {
    Patient findPatientByName(String name);
}
