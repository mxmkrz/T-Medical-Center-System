package com.t_systems.t_medical_center_system.dto;

import com.t_systems.t_medical_center_system.entity.Patient;
import lombok.Data;


@Data
public class AppointmentDto {
    private Patient patient;
    private String type;
    private Integer dose;
    private String timePattern;
}
