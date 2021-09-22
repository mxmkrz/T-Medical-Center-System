package com.t_systems.T_Medical_Center_System.converter;

import com.t_systems.T_Medical_Center_System.dto.AppointmentDto;
import com.t_systems.T_Medical_Center_System.dto.EventDto;
import com.t_systems.T_Medical_Center_System.entity.Appointment;
import com.t_systems.T_Medical_Center_System.entity.Event;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AppointmentConvertor{

    private final ModelMapper modelMapper;


    public AppointmentConvertor() {
        this.modelMapper = new ModelMapper();
    }

    public AppointmentDto convertToDto(Appointment entity) {
        return modelMapper.map(entity, AppointmentDto.class);
    }


}
