package com.t_systems.t_medical_center_system.mapper;

import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.entity.enums.PatientStatus;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class PatientMapper {

    private final ModelMapper modelMapper;
    private final Converter<String, String> doctorName = MappingContext::getSource;

    @Autowired
    public PatientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.createTypeMap(Patient.class, PatientDto.class).addMappings(mapper -> mapper.using(doctorName).map(Patient::getDoctorName, PatientDto::setDoctorsName));
    }

    public Patient toEntity(PatientDto patientDto) {
        return modelMapper.map(patientDto, Patient.class);
    }

    public PatientDto toDto(Patient patient) {
        return modelMapper.map(patient, PatientDto.class);
    }
}
