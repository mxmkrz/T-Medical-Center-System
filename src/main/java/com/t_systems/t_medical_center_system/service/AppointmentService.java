package com.t_systems.t_medical_center_system.service;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AppointmentService {

    Page<AppointmentDto> getAppointmentListByPatientId(Long id, Pageable pageable);

    void makeAnAppointment(AppointmentDto appointment, Long id);

    AppointmentDto getAppointById(Long id);

    void updateAppointment(AppointmentDto appointmentDto, Long idPatient);

    void cancelAppointment(AppointmentDto appointmentDto, Long idPatient);


}
