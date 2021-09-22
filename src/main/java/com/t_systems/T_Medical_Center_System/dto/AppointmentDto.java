package com.t_systems.T_Medical_Center_System.dto;

import com.t_systems.T_Medical_Center_System.entity.Patient;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
public class AppointmentDto {
    private Long id;
    private Patient patient;
    private String type;
    private Integer dose;
    private String timePattern;

}
