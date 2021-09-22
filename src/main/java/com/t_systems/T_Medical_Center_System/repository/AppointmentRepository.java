package com.t_systems.T_Medical_Center_System.repository;

import com.t_systems.T_Medical_Center_System.entity.Appointment;
import com.t_systems.T_Medical_Center_System.entity.Patient;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends PagingAndSortingRepository<Appointment,Long>{


}
