package com.t_systems.t_medical_center_system.repository;

import com.t_systems.t_medical_center_system.entity.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends PagingAndSortingRepository<Doctor, Long> {
    Doctor findDoctorByName(String name);

}
