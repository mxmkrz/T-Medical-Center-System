package com.t_systems.t_medical_center_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

/**
 * An entity that represents a doctor in database
 *
 * @author Kuryzin Maxim
 * @github mxmkrz
 */

@Entity
@Data
@Table(name = "tb_doctors")
@NoArgsConstructor
public class Doctor {
    @Id
    @SequenceGenerator(name = "doctor_id_generator", sequenceName = "doctor_id_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_id_generator")
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 20, message = "Name should be from 2 to 20 symbols")
    private String name;

    @NotBlank(message = "Surname is required")
    @Size(min = 2, max = 20, message = "Surname should be from 2 to 20 symbols")
    private String surname;

//    @NotBlank(message = "Password is required")
//    @Size(min = 8, max = 30, message = "Password should be from 8 to 30 symbols")
    private String password;

//    @Transient
//    @NotBlank(message = "PasswordConfirm is required")
//    @Size(min = 8, max = 30, message = "PasswordConfirm should be from 8 to 30 symbols")
    private String passwordConfirm;

    @NotBlank(message = "Position is required")
    private String position;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @CreationTimestamp
    private LocalDateTime createDataTime;

    @UpdateTimestamp
    private LocalDateTime updateDataTime;


    @ManyToMany(mappedBy = "doctors")
    private Set<Patient> patients = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;




}
