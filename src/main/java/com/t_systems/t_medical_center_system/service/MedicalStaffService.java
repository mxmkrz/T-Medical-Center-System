package com.t_systems.t_medical_center_system.service;

import com.t_systems.t_medical_center_system.dto.DoctorDto;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;

import java.util.List;

public interface MedicalStaffService {

    List<DoctorDto> getAllDoctors();

    void saveDoctor(MedicalStaff doctor);

    DoctorDto getById(Long id);

    void delete(Long id);

    void update(DoctorDto doctor);

    void saveNurse(MedicalStaff nurse);


}
