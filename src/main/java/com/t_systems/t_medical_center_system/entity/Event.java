package com.t_systems.t_medical_center_system.entity;

import com.t_systems.t_medical_center_system.entity.enums.EventStatus;
import com.t_systems.t_medical_center_system.entity.enums.TherapyType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "events")
@NoArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;


    private LocalTime time;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Enumerated(EnumType.STRING)
    private TherapyType therapyType;


    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    private String reasonToCancel;


    public Event(Date date, LocalTime time, EventStatus status, TherapyType therapyType, Appointment appointment, Patient patient) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.therapyType = therapyType;
        this.appointment = appointment;
        this.patient = patient;
    }
}
