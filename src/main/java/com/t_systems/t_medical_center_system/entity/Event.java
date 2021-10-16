package com.t_systems.t_medical_center_system.entity;

import com.t_systems.t_medical_center_system.entity.enums.EventStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@NoArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cause;


    private Date date;


    private String status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "date_and_time", nullable = false)
    private LocalDateTime dateAndTime;

//    @Column(name = "status", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private EventStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proc_id", nullable = false)
    private Procedure procedure;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_id", nullable = false)
    private Drug drug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}
//@Id
//@Column(name = "id")
//@GeneratedValue(strategy = GenerationType.IDENTITY)
//private int id;
//

//
//    @Column(name = "time")
//    private String time;
//

//
//    @Column(name = "remedy_name")
//    private String remedyName;
//
//    @Column(name = "remedy_type")
//    private String remedyType;
//
//    @Column(name = "quantity")
//    private int quantity;
//
//    @Column(name = "cause")
//    private String cause;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "prescription_id")
//    private Prescription prescription;
//
//    @Column(name = "patient_id")
//    private int patientId;

