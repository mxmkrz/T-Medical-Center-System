package com.t_systems.T_Medical_Center_System.component;

import com.t_systems.T_Medical_Center_System.dto.PatientDto;
import com.t_systems.T_Medical_Center_System.entity.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientConvector {

    private final ModelMapper modelMapper;

    public PatientConvector() {
        this.modelMapper = new ModelMapper();
    }


    public PatientDto convertToDto(Patient entity) {
        return modelMapper.map(entity, PatientDto.class);
    }


}
