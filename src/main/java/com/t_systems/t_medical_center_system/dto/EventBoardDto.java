package com.t_systems.t_medical_center_system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventBoardDto {
    private String date;
    private String time;
    private String patientName;
    private String patientSurname;
    private String status;
    private String therapyType;
    private String reasonToCancel;
}
