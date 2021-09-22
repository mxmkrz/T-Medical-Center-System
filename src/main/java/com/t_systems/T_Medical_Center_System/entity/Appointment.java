package com.t_systems.T_Medical_Center_System.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * This is my Appointment entity,here I will write the mapping in the database
 *
 * @author Kuryzin Maxim
 * @github mxmkrz
 */


@Entity
@Data
@Table(name = "tb_appointments")
public class Appointment {

    @Id
    @SequenceGenerator(name = "appointments_id_generator",sequenceName = "appointments_id_generator",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "appointments_id_generator")
    private Long id;
    @OneToOne ///attention pliz
    @JoinColumn(name = "patient_id",referencedColumnName = "id")
    private Patient patient;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer dose;

    @Column(nullable = false)
    private String timePattern;

    private LocalDateTime startDate;

    private LocalDateTime endData;

    @CreationTimestamp
    private LocalDateTime createDataTime;

    @UpdateTimestamp
    private LocalDateTime updateDataTime;

    public Appointment(){
    }

}
