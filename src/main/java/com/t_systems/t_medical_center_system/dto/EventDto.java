package com.t_systems.t_medical_center_system.dto;

import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.entity.enums.EventStatus;
import com.t_systems.t_medical_center_system.entity.enums.TherapyType;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class EventDto {
    private Long id;
    private Patient patient;
    private Date eventDateTime;
    private LocalTime time;
    private EventStatus status;
    private TherapyType therapyType;
    private String reasonToCancel;
    private Long idPatient;
    private Appointment appointment;
    private Long idAppointment;
}
