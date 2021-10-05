package com.t_systems.t_medical_center_system.service;

import com.t_systems.t_medical_center_system.dto.DoctorDto;
import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Doctor;
import com.t_systems.t_medical_center_system.entity.Patient;

import java.util.List;

public interface DoctorService {

    List<DoctorDto> getAllDoctors();

    void saveDoctor(Doctor doctor);

    DoctorDto getById(Long id);

    void delete(Long id);

    void update(DoctorDto doctor);


}
