package com.t_systems.t_medical_center_system.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_time_in_period")
public class TimePattern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time;
    @ManyToOne(fetch = FetchType.LAZY)
    private Appointment appointment;

}
