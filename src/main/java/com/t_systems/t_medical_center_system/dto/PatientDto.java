package com.t_systems.t_medical_center_system.dto;

import com.t_systems.t_medical_center_system.entity.enums.PatientStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PatientDto {
    private Long id;
    private String name;
    private String surname;
    private String diagnosis;
    private Long insuranceNumber;
    private String status;
    private String doctorsName;

}
