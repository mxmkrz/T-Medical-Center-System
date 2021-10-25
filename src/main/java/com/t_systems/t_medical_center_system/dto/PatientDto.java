package com.t_systems.t_medical_center_system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.*;
import java.util.Date;

@NoArgsConstructor
@Data
public class PatientDto {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Surname is required")
    private String surname;
    @NotBlank(message = "Diagnosis is required")
    private String diagnosis;
    @Min(value = 0, message = "Insurance Number should be greater than 0")
    private Long insuranceNumber;

    @NotBlank(message = "Status is required")
    private String status;

    private String doctorsName;
    private Date createDataTime;
    private Date updateDataTime;
}
