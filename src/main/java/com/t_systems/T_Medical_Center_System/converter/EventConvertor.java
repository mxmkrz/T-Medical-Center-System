package com.t_systems.T_Medical_Center_System.converter;

import com.t_systems.T_Medical_Center_System.dto.EventDto;
import com.t_systems.T_Medical_Center_System.dto.PatientDto;
import com.t_systems.T_Medical_Center_System.entity.Event;
import com.t_systems.T_Medical_Center_System.entity.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventConvertor {

    private final ModelMapper modelMapper;

    public EventConvertor() {
        this.modelMapper = new ModelMapper();
    }

    public EventDto convertToDto(Event entity) {
        return modelMapper.map(entity, EventDto.class);
    }

}
