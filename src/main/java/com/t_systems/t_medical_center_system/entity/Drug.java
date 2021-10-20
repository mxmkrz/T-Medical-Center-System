package com.t_systems.t_medical_center_system.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "appointment")
@Table(name = "tb_drug")
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    private Integer dosage;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;


    public Drug(String name, Integer dosage) {
        this.name = name;
        this.dosage = dosage;
    }
}
