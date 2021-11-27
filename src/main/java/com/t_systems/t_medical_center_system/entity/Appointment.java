package com.t_systems.t_medical_center_system.entity;

import com.t_systems.t_medical_center_system.entity.enums.AppointmentStatus;
import com.t_systems.t_medical_center_system.entity.enums.TherapyType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * An entity that represents  appointment in database
 *
 * @author Kuryzin Maxim
 * @github mxmkrz
 */
@Entity
@Table(name = "tb_appointment")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TherapyType therapyType;

    private Date startDate;

    private Date endDate;


    @OneToMany(mappedBy="appointment",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<WeekDay> weekDay;

    @OneToMany(mappedBy = "appointment",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Event> events;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private MedicalStaff staff;


    @OneToMany(mappedBy = "appointment",cascade = CascadeType.ALL)
    private List<Procedure> procedureList;

    @OneToMany(mappedBy = "appointment",cascade = CascadeType.ALL)
    private List<Drug> drugsList;


    @OneToMany(mappedBy = "appointment",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<EventTime> timePatterns;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

}
