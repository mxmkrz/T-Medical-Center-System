package com.t_systems.t_medical_center_system.dto;

import lombok.Data;


@Data
public class PatientDto {
    private Long id;
    private String name;
    private String surname;
    private String diagnosis;
    private Long insuranceNumber;

    public PatientDto() {
    }
}
