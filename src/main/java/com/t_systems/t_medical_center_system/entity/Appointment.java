package com.t_systems.t_medical_center_system.entity;

import com.t_systems.t_medical_center_system.entity.calendar.WeekDay;
import com.t_systems.t_medical_center_system.entity.enums.TherapyType;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;

import java.util.ArrayList;
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

    private Integer dosage;

    private String info;

    @OneToMany(mappedBy="appointment")
    private List<WeekDay> weekDay = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name="Nicknames", joinColumns=@JoinColumn(name="user_id"))
    @Column(name="nickname")
    public List<String> getNicknames;


    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private MedicalStaff staff;


    @OneToMany(mappedBy = "appointment")
    private List<Procedure> procedureList;

    @OneToMany(mappedBy = "appointment")
    private List<Drug> drugsList;

}
