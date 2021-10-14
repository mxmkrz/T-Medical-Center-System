package com.t_systems.t_medical_center_system.entity;

import com.t_systems.t_medical_center_system.entity.enums.PatientStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


/**
 * This is my Patient entity,here I will write the mapping in the database
 *
 * @author Kuryzin Maxim
 * @github mxmkrz
 */

@Entity
@Data
@Table(name = "tb_patients")
@NoArgsConstructor
public class Patient {

    @Id
    @SequenceGenerator(name = "patient_id_generator", sequenceName = "patient_id_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_id_generator")
    private Long id;

    private String name;

    private String surname;


    private String diagnosis;

    private Long insuranceNumber;

    @CreationTimestamp
    private Date createDataTime;

    @UpdateTimestamp
    private Date updateDataTime;

    @Enumerated(EnumType.STRING)
    private PatientStatus patientStatus;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointmentList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "patient_medical_staff",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "medical_staff_id"))
    private Set<MedicalStaff> medicalStaffs = new HashSet<>();



}