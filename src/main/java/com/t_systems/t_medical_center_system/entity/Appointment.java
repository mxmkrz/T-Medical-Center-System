package com.t_systems.t_medical_center_system.entity;

import com.t_systems.t_medical_center_system.entity.calendar.WeekDay;
import com.t_systems.t_medical_center_system.entity.enums.TherapyType;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.util.Date;
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


    private Date startDate;

    private Date endDate;


    @OneToMany(mappedBy="appointment",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<WeekDay> weekDay;

//    @OneToMany(mappedBy = "appointment",fetch = FetchType.EAGER)
//    private List<Event> events;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private MedicalStaff staff;


    @OneToMany(mappedBy = "appointment",cascade = CascadeType.ALL)
    private List<Procedure> procedureList;

    @OneToMany(mappedBy = "appointment",cascade = CascadeType.ALL)
    private List<Drug> drugsList;

    @OneToMany(mappedBy = "appointment",cascade = CascadeType.ALL)
    private List<EventTime> timePatterns;




}
