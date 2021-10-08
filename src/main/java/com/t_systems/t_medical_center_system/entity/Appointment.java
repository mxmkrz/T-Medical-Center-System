package com.t_systems.t_medical_center_system.entity;

import com.t_systems.t_medical_center_system.entity.calendar.WeekDay;
import com.t_systems.t_medical_center_system.entity.enums.TherapyType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "tb_appointment")
@Data
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TherapyType therapyType;


    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String dosage;


    @Enumerated(EnumType.STRING)
    private WeekDay weekDay;

    private LocalDateTime time;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private MedicalStaff staff;


    @OneToMany(mappedBy = "appointment")
    private List<Procedure> procedureList;

    @OneToMany(mappedBy = "appointment")
    private List<Drug> drugsList;


    private String description;




}
