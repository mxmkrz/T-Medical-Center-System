package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.mapper.Convertor;
import com.t_systems.t_medical_center_system.dto.DoctorDto;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.entity.enums.Role;
import com.t_systems.t_medical_center_system.exception.DoctorNotFoundException;
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
import java.util.List;
import java.util.Set;


@Slf4j
@Service
public class MedicalStaffServiceImp implements MedicalStaffService, UserDetailsService {

    private Convertor<MedicalStaff, DoctorDto> doctorConvertor;
    private MedicalStaffRepository staffRepository;
    private PatientServiceImp patientServiceImp;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MedicalStaffServiceImp(Convertor<MedicalStaff, DoctorDto> doctorConvertor, MedicalStaffRepository doctorRepository, PatientServiceImp patientServiceImp, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.doctorConvertor = doctorConvertor;
        this.staffRepository = doctorRepository;
        this.patientServiceImp = patientServiceImp;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Transactional(readOnly = true)
    @Override
    public List<DoctorDto> getAllDoctors() {
        return doctorConvertor.convertLisToDto((List<MedicalStaff>) staffRepository.findAll(), DoctorDto.class);
    }

    @Transactional
    @Override
    public void saveStaff(MedicalStaff doctor) {
        doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
        staffRepository.save(doctor);
        log.info("Add doctor");
    }

    @Transactional(readOnly = true)
    @Override
    public DoctorDto getById(Long id) {
        return doctorConvertor.convertToDto(staffRepository.findById(id).orElseThrow(DoctorNotFoundException::new), DoctorDto.class);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        staffRepository.deleteById(id);
        log.info("Delete doctor");
    }

    @Transactional
    @Override
    public void update(DoctorDto doctorDto) {
        staffRepository.save(doctorConvertor.convertToEntity(doctorDto, MedicalStaff.class));
    }

    @Override
    public void saveNurse(MedicalStaff nurse) {
        nurse.setRole(Role.NURSE);
        nurse.setPassword(bCryptPasswordEncoder.encode(nurse.getPassword()));
        staffRepository.save(nurse);
        log.info("Add nurse");
    }


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        MedicalStaff medicalStaff = staffRepository.findByName(name);
        System.out.println(medicalStaff);
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
