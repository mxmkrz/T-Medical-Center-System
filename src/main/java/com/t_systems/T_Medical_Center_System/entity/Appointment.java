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
@ToString
@Table(name = "tb_appointments")
public class Appointment {
    @Getter
    @Setter
    @Id
    @SequenceGenerator(name = "appointments_id_generator",sequenceName = "appointments_id_generator",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "appointments_id_generator")
    private Long id;
    @Getter
    @Setter
    @OneToOne ///attention pliz
    @JoinColumn(name = "patient_id",referencedColumnName = "id")
    private Patient patient;
    @Getter
    @Setter
    @Column(nullable = false)
    private String type;
    @Getter
    @Setter
    @Column(nullable = false)
    private Integer dose;
    @Getter
    @Setter
    @Column(nullable = false)
    private String timePattern;
    @Getter
    @Setter
    private LocalDateTime startDate;
    @Getter
    @Setter
    private LocalDateTime endData;
    @Getter
    @Setter
    @CreationTimestamp
    private LocalDateTime createDataTime;
    @Getter
    @Setter
    @UpdateTimestamp
    private LocalDateTime updateDataTime;

    public Appointment(){
    }

}
