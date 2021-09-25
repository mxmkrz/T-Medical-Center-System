package com.t_systems.T_Medical_Center_System.repository;

import com.t_systems.T_Medical_Center_System.entity.Patient;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {
    @Query(value = "SELECT c FROM Patient c")
    List<Patient> findAllList();




}
