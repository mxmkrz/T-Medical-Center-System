package com.t_systems.t_medical_center_system.mapper;

import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Patient;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class PatientMapper {

    private final ModelMapper modelMapper;
    private final Converter<String, String> doctorName = MappingContext::getSource;
    private final Converter<String, String> doctorEmail = MappingContext::getSource;


    @Autowired
    public PatientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.createTypeMap(Patient.class, PatientDto.class).addMappings(mapper -> mapper.using(doctorName).map(Patient::getDoctorName, PatientDto::setDoctorsName))
                .addMappings(mapper -> mapper.using(doctorEmail).map(Patient::getDoctorEmail, PatientDto::setDoctorsEmail));
    }

    public Patient toEntity(PatientDto patientDto) {
        return modelMapper.map(patientDto, Patient.class);
    }

    public PatientDto toDto(Patient patient) {
        return modelMapper.map(patient, PatientDto.class);
    }
}
