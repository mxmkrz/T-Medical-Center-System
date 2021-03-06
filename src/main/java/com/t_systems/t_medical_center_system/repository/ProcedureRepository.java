package com.t_systems.t_medical_center_system.repository;


import com.t_systems.t_medical_center_system.entity.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedureRepository extends PagingAndSortingRepository<Procedure,Long> {
    List<Procedure> findProcedureByAppointmentId(Long id);
}
