package com.t_systems.t_medical_center_system.repository;

import com.t_systems.t_medical_center_system.entity.EventTime;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventTimeRepository  extends PagingAndSortingRepository<EventTime,Long> {
    List<EventTime> findAllByAppointmentId(Long id);
}
