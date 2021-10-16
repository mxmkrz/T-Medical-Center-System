package com.t_systems.t_medical_center_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tb_time_in_period")
@NoArgsConstructor
public class EventTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Appointment appointment;

    public EventTime(LocalDateTime time) {
        this.time = time;
    }
}
