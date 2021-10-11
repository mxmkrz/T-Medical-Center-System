package com.t_systems.t_medical_center_system.dto;

import com.t_systems.t_medical_center_system.entity.Patient;
import lombok.Data;

import java.util.HashMap;
import java.util.List;


@Data
public class AppointmentDto {

    private String type;
    private Integer dose;
    private String startData;
    private String endData;
    private HashMap<String, List<String>> dayAndTime;
}
