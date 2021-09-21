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
@ToString
@Table(name = "tb_patients")
public class Patient {
    @Getter
    @Setter
    @Id
    @SequenceGenerator(name = "patient_id_generator",sequenceName = "patient_id_generator",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "patient_id_generator")
    private Long id;
    @Getter
    @Setter
    @Column(nullable = false)
    private String firstName;
    @Getter
    @Setter
    @Column(nullable = false)
    private String secondName;
    @Getter
    @Setter
    @Column(nullable = false)
    private String diagnosis;
    @Getter
    @Setter
    @Column(nullable = false,unique = true)
    private Long insuranceNumber;
    @Getter
    @Setter
    @Column(nullable = false)
    private String doctorsName;
    @Getter
    @Setter
    @CreationTimestamp
    private LocalDateTime createDataTime;
    @Getter
    @Setter
    @UpdateTimestamp
    private LocalDateTime updateDataTime;
    @Getter
    @Setter
    private Boolean status;

    public Patient() {
    }

}
