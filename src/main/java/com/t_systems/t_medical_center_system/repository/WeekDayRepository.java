package com.t_systems.t_medical_center_system.repository;

import com.t_systems.t_medical_center_system.entity.WeekDay;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WeekDayRepository extends PagingAndSortingRepository<WeekDay,Long> {
    List<WeekDay> findAllByAppointmentId(Long id);

}
