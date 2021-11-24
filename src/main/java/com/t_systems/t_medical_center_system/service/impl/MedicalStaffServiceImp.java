package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.repository.MedicalStaffRepository;
import com.t_systems.t_medical_center_system.service.MedicalStaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
public class MedicalStaffServiceImp implements MedicalStaffService, UserDetailsService {

    private final MedicalStaffRepository staffRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MedicalStaffRepository medicalStaffRepository;
    private final JavaMailSender mailSender;
    private final SimpleMailMessage mailMessage;
    @Autowired
    public MedicalStaffServiceImp(MedicalStaffRepository staffRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MedicalStaffRepository medicalStaffRepository, JavaMailSender mailSender, SimpleMailMessage mailMessage) {
        this.staffRepository = staffRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.medicalStaffRepository = medicalStaffRepository;
        this.mailSender = mailSender;
        this.mailMessage = mailMessage;
    }





    @Transactional
    @Override
    public void saveStaff(MedicalStaff doctor) {
        doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
        staffRepository.save(doctor);
        log.info("Add medical staff");
    }


    @Override
    public void sendLinkOnEmail(String email) {
        mailMessage.setTo(email);
        mailMessage.setText("Please follow the links to change your password http://localhost:8080/login/change");
        mailSender.send(mailMessage);
    }

    @Transactional
    @Override
    public void changePassword(MedicalStaff medicalStaff) {
        List<MedicalStaff> medicalStaffList = (List<MedicalStaff>) medicalStaffRepository.findAll();
        for (MedicalStaff m : medicalStaffList) {
            if (bCryptPasswordEncoder.matches(medicalStaff.getOldPassword(), m.getPassword())) {
                m.setPassword(bCryptPasswordEncoder.encode(medicalStaff.getNewPassword()));
                medicalStaffRepository.save(m);
            }
        }
    }


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MedicalStaff medicalStaff = staffRepository.findByEmail(email);
        if (medicalStaff == null) {
            throw new UsernameNotFoundException(email);
        }
        String role = medicalStaff.getRole().name();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(authority);

        return new org.springframework.security.core.userdetails.User(medicalStaff.getEmail(), medicalStaff.getPassword(), roles);
    }


}
