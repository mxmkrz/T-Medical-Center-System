package com.t_systems.t_medical_center_system.dto;

import lombok.Data;

@Data
public class DoctorDto {
    private Long id;
    private String name;
    private String surname;
    private String position;
    private String specialization;
}
