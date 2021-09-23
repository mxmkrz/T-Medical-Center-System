package com.t_systems.T_Medical_Center_System.dto;

import com.t_systems.T_Medical_Center_System.entity.Patient;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

@Data
public class EventDto {
    private Patient patient;
    private LocalDateTime eventDateTime;
    private String status;
}
