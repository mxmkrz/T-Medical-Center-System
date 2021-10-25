package com.t_systems.t_medical_center_system.repository;

import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends PagingAndSortingRepository<Appointment, Long> ,JpaRepository<Appointment,Long>{
    List<Appointment> findAllByPatient(Patient patient);

    List<Appointment> findAllByPatientId(Long id);











}
