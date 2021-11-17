package com.t_systems.t_medical_center_system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_week_day")
@Setter
@Getter
@NoArgsConstructor
public class WeekDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String day;

    @ManyToOne
    @JoinColumn(name="appointment_id")
    private Appointment appointment;


    public WeekDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "day='" + day + '\'';
    }
}
