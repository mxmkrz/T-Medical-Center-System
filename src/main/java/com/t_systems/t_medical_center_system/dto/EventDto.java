package com.t_systems.t_medical_center_system.dto;

import com.t_systems.t_medical_center_system.entity.Patient;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDto {
    private Patient patient;
    private LocalDateTime eventDateTime;
    private String status;
}
