package com.t_systems.T_Medical_Center_System.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * This is my Patient entity,here I will write the mapping in the database
 *
 * @author Kuryzin Maxim
 * @github mxmkrz
 */

@Entity
@Data
@Table(name = "tb_patients")
public class Patient {

    @Id
    @SequenceGenerator(name = "patient_id_generator",sequenceName = "patient_id_generator",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "patient_id_generator")

    private Long id;

    private String firstName;

    private String secondName;

    private String diagnosis;

    private Long insuranceNumber;

    private String doctorsName;

    @CreationTimestamp
    private LocalDateTime createDataTime;

    @UpdateTimestamp
    private LocalDateTime updateDataTime;

    private Boolean status;

    public Patient() {
    }

    public Patient(String firstName, String secondName, String diagnosis, Long insuranceNumber, String doctorsName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.diagnosis = diagnosis;
        this.insuranceNumber = insuranceNumber;
        this.doctorsName = doctorsName;
    }
}