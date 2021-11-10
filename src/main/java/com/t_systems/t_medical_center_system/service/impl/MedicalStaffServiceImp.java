package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.repository.MedicalStaffRepository;
import com.t_systems.t_medical_center_system.service.MedicalStaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.Set;


@Slf4j
@Service
public class MedicalStaffServiceImp implements MedicalStaffService, UserDetailsService {

    private final MedicalStaffRepository staffRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MedicalStaffServiceImp(MedicalStaffRepository doctorRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.staffRepository = doctorRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Transactional
    @Override
    public void saveStaff(MedicalStaff doctor) {
        doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
        staffRepository.save(doctor);
        log.info("Add medical staff");
    }


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        MedicalStaff medicalStaff = staffRepository.findByName(name);
        if (medicalStaff == null) {
            throw new UsernameNotFoundException(name);
        }
        String role = medicalStaff.getRole().name();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(authority);

        return new org.springframework.security.core.userdetails.User(medicalStaff.getName(), medicalStaff.getPassword(), roles);
    }
}
