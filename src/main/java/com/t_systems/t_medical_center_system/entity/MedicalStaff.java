package com.t_systems.t_medical_center_system.entity;

import com.t_systems.t_medical_center_system.entity.enums.Role;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * An entity that represents a doctor in database
 *
 * @author Kuryzin Maxim
 * @github mxmkrz
 */

@Entity
@Setter
@Getter
@EqualsAndHashCode
@Table(name = "tb_medical_staff")
@NoArgsConstructor
public class MedicalStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String password;
    @Email(message = "Incorrect email format")
    private String email;
    @CreationTimestamp
    private LocalDateTime createDataTime;
    @UpdateTimestamp
    private LocalDateTime updateDataTime;
    @Enumerated(EnumType.STRING)
    private Role role;



    @Transient
    private String oldPassword;
    @Transient
//    @Size(min = 6, message = "New password cannot be less than 6 symbol")
    private String newPassword;
    @Transient
//    @Size(min = 6, message = " Confirm password cannot be less than 6 symbol")
    private String confirmPassword;

}
