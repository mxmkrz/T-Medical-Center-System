package com.t_systems.T_Medical_Center_System.security;

import com.t_systems.T_Medical_Center_System.service.PatientService;
import com.t_systems.T_Medical_Center_System.service.impl.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PatientDetailsServiceImpl implements UserDetailsService {

    private PatientServiceImp patientServiceImp;

    @Autowired
    public PatientDetailsServiceImpl(PatientServiceImp patientServiceImp) {
        this.patientServiceImp = patientServiceImp;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
