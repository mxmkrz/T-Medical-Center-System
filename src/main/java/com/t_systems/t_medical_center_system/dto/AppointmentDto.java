package com.t_systems.t_medical_center_system.dto;

import com.t_systems.t_medical_center_system.entity.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;


@Data
@NoArgsConstructor
public class AppointmentDto {
    private String type;
    private Integer dose;
    private String startData;
    private String endData;

    public AppointmentDto(String type, Integer dose, String startData, String endData) {
        this.type = type;
        this.dose = dose;
        this.startData = startData;
        this.endData = endData;
    }
}
