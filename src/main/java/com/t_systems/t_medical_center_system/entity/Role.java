package com.t_systems.t_medical_center_system.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    DOCTOR, NURSE;

    @Override
    public String getAuthority() {
        return name();
    }
}
