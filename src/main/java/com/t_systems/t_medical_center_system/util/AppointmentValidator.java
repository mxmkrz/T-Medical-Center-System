package com.t_systems.t_medical_center_system.util;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Component
public class AppointmentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return AppointmentDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AppointmentDto appointment = (AppointmentDto) o;
        List<Boolean> weekDay = new ArrayList<>();
        weekDay.add(appointment.isMonday());
        weekDay.add(appointment.isTuesday());
        weekDay.add(appointment.isWednesday());
        weekDay.add(appointment.isThursday());
        weekDay.add(appointment.isFriday());
        weekDay.add(appointment.isSaturday());
        weekDay.add(appointment.isSunday());
        int counter = 0;
        for (Boolean w:weekDay) {
            if (w) counter++;
        }
        if (counter == 0){
            errors.rejectValue("saturday","", "WeekDays should not be empty");
        }
        if (appointment.getInfo().isEmpty() && appointment.getInfoDrugs().isEmpty()){
            errors.rejectValue("info","", "Info should not be empty");
        }

    }
}
