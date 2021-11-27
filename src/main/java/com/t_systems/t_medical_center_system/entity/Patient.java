package com.t_systems.t_medical_center_system.entity;

import com.t_systems.t_medical_center_system.entity.enums.PatientStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


/**
 * An entity that represents a patient in database
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
@AllArgsConstructor
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String diagnosis;
    private Long insuranceNumber;
    private String doctorName;
    private String doctorEmail;

    @CreationTimestamp
    private Date createDataTime;

    @UpdateTimestamp
    private Date updateDataTime;

    @Enumerated(EnumType.STRING)
    private PatientStatus patientStatus;


}