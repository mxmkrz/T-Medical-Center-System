package com.t_systems.t_medical_center_system.service;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppointmentService {

    Page<AppointmentDto> getAppointmentListByPatientId(Long id, Pageable pageable);

    void makeAnAppointment(AppointmentDto appointment, Long id);

    AppointmentDto getAppointById(Long id);

    void updateAppointment(AppointmentDto appointmentDto, Long idPatient);

    void cancelOrDoneAppointment(AppointmentDto appointmentDto, Long idPatient);

    void checkStatusEvents(Long id);


}
