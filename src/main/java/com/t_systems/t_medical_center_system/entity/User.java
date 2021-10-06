package com.t_systems.t_medical_center_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Size(min = 7, message = "Minimum 7 symbols")
    private String password;

    @Column(name = "users_role")
    private Role role;


    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private Patient patient;


    @OneToOne(mappedBy = "user")
    private Doctor doctor;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return role.name();
    }
}