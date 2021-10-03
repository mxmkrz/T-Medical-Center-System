package com.t_systems.t_medical_center_system.dto;

import lombok.Data;


@Data
public class PatientDto {
    private Long id;
    private String firstName;
    private String secondName;
    private String diagnosis;
    private Long insuranceNumber;
    private String doctorsName;
    private Boolean status;

    public PatientDto() {
    }
}
