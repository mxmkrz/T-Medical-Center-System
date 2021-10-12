package com.t_systems.t_medical_center_system.service;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.entity.Appointment;

import java.util.List;

public interface AppointmentService {

    List<AppointmentDto> getAllAppointments();

    void addAppointment(AppointmentDto appointment,Long id);

}
