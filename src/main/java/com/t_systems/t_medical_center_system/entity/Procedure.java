package com.t_systems.t_medical_center_system.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = "appointment")
@Table(name = "tb_procedure")

public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    public Procedure(String name) {
        this.name = name;
    }
}
