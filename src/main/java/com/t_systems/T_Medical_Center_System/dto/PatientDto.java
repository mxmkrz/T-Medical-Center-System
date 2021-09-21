package com.t_systems.T_Medical_Center_System.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class PatientDto {
    private Long id;
    private String firstName;
    private String secondName;
    private String diagnosis;
    private Long insuranceNumber;
    private String doctorsName;
    private LocalDateTime createDataTime;
    private LocalDateTime updateDataTime;
    private Boolean status;

}
