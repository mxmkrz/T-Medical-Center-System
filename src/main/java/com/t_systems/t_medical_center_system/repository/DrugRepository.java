package com.t_systems.t_medical_center_system.repository;

import com.t_systems.t_medical_center_system.entity.Drug;
import com.t_systems.t_medical_center_system.entity.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugRepository extends PagingAndSortingRepository<Drug,Long> {
    List<Drug> findAllByAppointmentId(Long id);
}

