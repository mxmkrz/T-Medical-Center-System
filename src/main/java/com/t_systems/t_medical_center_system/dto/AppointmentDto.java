package com.t_systems.t_medical_center_system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.entity.calendar.Time;
import com.t_systems.t_medical_center_system.entity.calendar.WeekDay;
import com.t_systems.t_medical_center_system.entity.enums.TherapyType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.*;


@Data

public class AppointmentDto {
    @Enumerated(EnumType.STRING)
    private TherapyType type;
    private Integer dose;
    private String info;


    private Date startOfData;
    private Date endOfData;



    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;

    private ArrayList<String> time = new ArrayList<>() {{
        add("9:00 - 10:00");
        add("10:00 - 11:00");
        add("11:00 - 12:00");
        add("12:00 - 13:00");
        add("13:00 - 14:00");
        add("14:00 - 15:00");
        add("15:00 - 16:00");
        add("16:00 - 17:00");
        add("17:00 - 18:00");
        add("18:00 - 19:00");
        add("19:00 - 20:00");
        add("20:00 - 21:00");
    }};





    public AppointmentDto() {
    }



}
