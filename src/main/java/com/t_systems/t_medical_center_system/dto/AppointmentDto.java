package com.t_systems.t_medical_center_system.dto;

import com.t_systems.t_medical_center_system.entity.enums.AppointmentStatus;
import com.t_systems.t_medical_center_system.entity.enums.TherapyType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;


@Data
@NoArgsConstructor
public class AppointmentDto {
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Therapy Type should not be empty")
    private TherapyType type;
    @Min(1)
    private Integer dose;
    private String infoDrugs;
    private String info;
    @Future(message = "Start date must be less than current")
    @DateTimeFormat(pattern = "MM/dd/yyyy h:mm a")
    @NotNull(message = "Start date should not be empty")
    private Date startOfData;
    @DateTimeFormat(pattern = "MM/dd/yyyy h:mm a")
    @NotNull(message = "Date should not be empty")
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
    @NotEmpty(message = "Time list cannot be empty.")
    private ArrayList<String> time = new ArrayList<>();
    private List<String> weekDayString = new ArrayList<>();
    private List<String> eventTimes = new ArrayList<>();


}
