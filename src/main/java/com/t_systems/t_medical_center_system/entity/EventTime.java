package com.t_systems.t_medical_center_system.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * An entity that represents a time even in database
 *
 * @author Kuryzin Maxim
 * @github mxmkrz
 */
@Entity
@Getter
@Setter
@Table(name = "tb_time_in_period")
@NoArgsConstructor
public class EventTime implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @Override
    public String toString() {
        return "time=" + time ;
    }
}
