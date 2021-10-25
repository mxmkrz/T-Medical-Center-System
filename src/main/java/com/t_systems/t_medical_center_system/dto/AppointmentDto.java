package com.t_systems.t_medical_center_system.dto;

import com.t_systems.t_medical_center_system.entity.enums.AppointmentStatus;
import com.t_systems.t_medical_center_system.entity.enums.TherapyType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;


@Data
@NoArgsConstructor
public class AppointmentDto {
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "type should not be empty")
    private TherapyType type;
    @Min(1)
    private Integer dose;
//    @NotBlank(message = "infoDrugs should not be empty")
    private String infoDrugs;
//    @NotBlank(message = "info should not be empty")
    private String info;

    @NotNull(message = "date should not be empty")
    private Date startOfData;
    @NotNull(message = "date should not be empty")
    private Date endOfData;

    private boolean sunday;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @NotEmpty(message = "time list cannot be empty.")
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

    List<String> weekDayString = new ArrayList<>();
    List<String> eventTimes = new ArrayList<>();


}
