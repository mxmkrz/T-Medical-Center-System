package com.t_systems.t_medical_center_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.*;
import java.util.Date;

@AllArgsConstructor
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
    @NotNull(message = "Insurance empty")
    private Long insuranceNumber;
    @NotBlank(message = "Status is required")
    private String status;
    private String doctorsName;
    private String doctorsEmail;
    private Date createDataTime;
    private Date updateDataTime;
}
