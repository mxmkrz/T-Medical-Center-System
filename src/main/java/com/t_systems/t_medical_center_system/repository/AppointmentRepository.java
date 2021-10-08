package com.t_systems.t_medical_center_system.repository;

import com.t_systems.t_medical_center_system.entity.Appointment;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AppointmentRepository extends PagingAndSortingRepository<Appointment,Long> {
    List<Appointment> findAllByPatientId(Long id);
}
