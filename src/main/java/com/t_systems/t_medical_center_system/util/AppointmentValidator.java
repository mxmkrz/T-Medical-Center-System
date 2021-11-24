package com.t_systems.t_medical_center_system.util;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.entity.EventTime;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        for (Boolean w : weekDay) {
            if (w) counter++;
        }
        if (counter == 0) {
            errors.rejectValue("saturday", "", "WeekDays should not be empty");
        }
        if (appointment.getInfo().isEmpty() && appointment.getInfoDrugs().isEmpty()) {
            errors.rejectValue("info", "", "Info should not be empty");
        }





        LocalDate date = appointment.getStartOfData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (date.equals(LocalDate.now())) {
            List<LocalTime> timeList = new ArrayList<>();
            for (int i = 0; i < appointment.getTime().size(); i++) {
                switch (appointment.getTime().get(i)) {
                    case ("0") -> timeList.add(LocalTime.of(9, 0));
                    case ("1") -> timeList.add(LocalTime.of(10, 0));
                    case ("2") -> timeList.add(LocalTime.of(11, 0));
                    case ("3") -> timeList.add(LocalTime.of(12, 0));
                    case ("4") -> timeList.add(LocalTime.of(13, 0));
                    case ("5") -> timeList.add(LocalTime.of(14, 0));
                    case ("6") -> timeList.add(LocalTime.of(15, 0));
                    case ("7") -> timeList.add(LocalTime.of(16, 0));
                    case ("8") -> timeList.add(LocalTime.of(17, 0));
                    case ("9") -> timeList.add(LocalTime.of(18, 0));
                    case ("10") -> timeList.add(LocalTime.of(19, 0));
                    case ("11") -> timeList.add(LocalTime.of(20, 0));
                }
            }
            for (LocalTime l : timeList) {
                if (l.isBefore(LocalTime.now())) {
                    errors.rejectValue("time", "", "This " + l.getHour() + " cannot be less than the current " + LocalDateTime.now().getHour() + " hours");
                }

            }
        }


    }
}
