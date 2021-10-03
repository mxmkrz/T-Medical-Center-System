package com.t_systems.t_medical_center_system.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


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
    @SequenceGenerator(name = "patient_id_generator", sequenceName = "patient_id_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_id_generator")
    private Long id;

    private String firstName;

    private String secondName;

    private String diagnosis;

    private Long insuranceNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctorsName;

    @CreationTimestamp
    private LocalDateTime createDataTime;

    @UpdateTimestamp
    private LocalDateTime updateDataTime;

    private Boolean status;
    @ManyToMany
    @JoinTable(name = "patient_medical_doctor",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private Set<Doctor> doctors = new HashSet<>();


    public Patient() {
    }

    public Patient(String firstName, String secondName, String diagnosis, Long insuranceNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.diagnosis = diagnosis;
        this.insuranceNumber = insuranceNumber;

    }
}