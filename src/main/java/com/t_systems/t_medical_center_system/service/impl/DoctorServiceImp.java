package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.converter.Convertor;
import com.t_systems.t_medical_center_system.dto.DoctorDto;
import com.t_systems.t_medical_center_system.entity.Doctor;
import com.t_systems.t_medical_center_system.entity.Role;
import com.t_systems.t_medical_center_system.exception.DoctorNotFoundException;
import com.t_systems.t_medical_center_system.repository.DoctorRepository;
import com.t_systems.t_medical_center_system.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Slf4j
@Service
public class DoctorServiceImp implements DoctorService {

    private Convertor<Doctor, DoctorDto> doctorConvertor;
    private DoctorRepository doctorRepository;
    private PatientServiceImp patientServiceImp;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DoctorServiceImp(Convertor<Doctor, DoctorDto> doctorConvertor, DoctorRepository doctorRepository, PatientServiceImp patientServiceImp, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.doctorConvertor = doctorConvertor;
        this.doctorRepository = doctorRepository;
        this.patientServiceImp = patientServiceImp;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Transactional(readOnly = true)
    @Override
    public List<DoctorDto> getAllDoctors() {
        return doctorConvertor.convertLisToDto((List<Doctor>) doctorRepository.findAll(), DoctorDto.class);
    }

    @Transactional
    @Override
    public void saveDoctor(Doctor doctor) {
        doctor.setRole(new Role(1L,"ROLE_DOCTOR"));
        doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
        doctorRepository.save(doctor);
        log.info("Add doctor");
    }

    @Transactional(readOnly = true)
    @Override
    public DoctorDto getById(Long id) {
        return doctorConvertor.convertToDto(doctorRepository.findById(id).orElseThrow(DoctorNotFoundException::new), DoctorDto.class);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        doctorRepository.deleteById(id);
        log.info("Delete doctor");
    }

    @Transactional
    @Override
    public void update(DoctorDto doctorDto) {
        doctorRepository.save(doctorConvertor.convertToEntity(doctorDto, Doctor.class));
    }


}
