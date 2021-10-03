package com.t_systems.t_medical_center_system.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_nurse")
public class Nurse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
