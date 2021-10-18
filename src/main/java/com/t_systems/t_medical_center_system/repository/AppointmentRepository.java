package com.t_systems.t_medical_center_system.repository;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.entity.Procedure;
import com.t_systems.t_medical_center_system.entity.calendar.WeekDay;
import com.t_systems.t_medical_center_system.entity.enums.TherapyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends PagingAndSortingRepository<Appointment, Long> ,JpaRepository<Appointment,Long>{
    List<Appointment> findAllByPatient(Patient patient);






}
