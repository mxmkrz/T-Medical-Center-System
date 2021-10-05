package com.t_systems.t_medical_center_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
@Entity
@Table(name = "t_role")
@Data
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private String name;
    @Transient
    @OneToMany(mappedBy = "role")
    private Set<Doctor> doctors;

    @Transient
    @OneToMany(mappedBy = "role")
    private Set<Patient> patients;


    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String getAuthority() {
        return getName();
    }
}