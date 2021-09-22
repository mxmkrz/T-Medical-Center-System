package com.t_systems.T_Medical_Center_System.converter;

import com.t_systems.T_Medical_Center_System.dto.PatientDto;
import com.t_systems.T_Medical_Center_System.entity.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class PatientConvertor {

    private final ModelMapper modelMapper;


    public PatientConvertor() {
        this.modelMapper = new ModelMapper();
    }

    public PatientDto convertToDto(Patient entity) {
        return modelMapper.map(entity, PatientDto.class);
    }

    public Patient convertToEntity(PatientDto dto) {
        return modelMapper.map(dto, Patient.class);
    }
}
