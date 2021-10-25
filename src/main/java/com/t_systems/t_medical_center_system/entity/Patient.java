package com.t_systems.t_medical_center_system.entity;

import com.t_systems.t_medical_center_system.entity.enums.PatientStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;


/**
 * This is my Patient entity,here I will write the mapping in the database
 *
 * @author Kuryzin Maxim
 * @github mxmkrz
 */

@Entity
@Setter
@Getter
@EqualsAndHashCode
@Table(name = "tb_patients")
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private String surname;


    private String diagnosis;

    private Long insuranceNumber;

    private String doctorName;

    @CreationTimestamp
    private Date createDataTime;

    @UpdateTimestamp
    private Date updateDataTime;

    @Enumerated(EnumType.STRING)
    private PatientStatus patientStatus;

}