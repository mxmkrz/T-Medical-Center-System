package com.t_systems.t_medical_center_system.mapper;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.calendar.WeekDay;
import com.t_systems.t_medical_center_system.entity.enums.TherapyType;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AppointmentMapper {



    public static Appointment toEntity(AppointmentDto appointmentDto){

        Appointment appointment = new Appointment();
        appointment.setTherapyType(appointmentDto.getType());
        appointment.setStartDate(appointmentDto.getStartOfData());
        appointment.setEndDate(appointmentDto.getEndOfData());
        appointment.setDosage(appointmentDto.getDose());
        appointment.setInfo(appointmentDto.getInfo());
        List<String> dayWeek = new ArrayList<>(List.of(appointmentDto.getMonday(), appointmentDto.getTuesday(), appointmentDto.getWednesday()
        , appointmentDto.getThursday(), appointmentDto.getFriday(), appointmentDto.getSaturday(), appointmentDto.getSunday()));
        List<String> dayWeekFilter =  dayWeek.stream().filter(i -> i.equals("on")).collect(Collectors.toList());
        for (String w:appointment.getWeekDay()) {
            w.setDay(dayWeekFilter);
        }

        return appointment;


    }


    public static void main(String[] args) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setType(TherapyType.DRUG);
        appointmentDto.setDose(3242432);
//        appointmentDto.setMonday("on");
//        appointmentDto.setFriday("on");
        System.out.println(AppointmentMapper.toEntity(appointmentDto));
        ;
    }












}
