package com.t_systems.T_Medical_Center_System.dto;

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
