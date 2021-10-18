package com.t_systems.t_medical_center_system.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = "appointment")
@Table(name = "tb_time_in_period")
@NoArgsConstructor
public class EventTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    public EventTime(LocalDateTime time) {
        this.time = time;
    }
}
