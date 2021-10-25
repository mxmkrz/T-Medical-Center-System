package com.t_systems.t_medical_center_system.service;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.entity.Appointment;

import java.util.List;

public interface AppointmentService {

    List<AppointmentDto> getAppointmentListByPatientId(Long id);
    void makeAnAppointment(AppointmentDto appointment, Long id);
    AppointmentDto gitAppointById(Long id);
    void updateAppointment(AppointmentDto appointmentDto, Long idPatient);
    void cancelAppointment(AppointmentDto appointmentDto,Long idPatient);
}
