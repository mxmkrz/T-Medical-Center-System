package com.t_systems.t_medical_center_system.entity;

import com.t_systems.t_medical_center_system.entity.calendar.WeekDay;
import com.t_systems.t_medical_center_system.entity.enums.TherapyType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_appointment")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TherapyType therapyType;


    private Date startDate;

    private Date endDate;


    @OneToMany(mappedBy="appointment",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<WeekDay> weekDay;

//    @OneToMany(mappedBy = "appointment",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    private List<Event> events;


    @ManyToOne(cascade = CascadeType.ALL)
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
