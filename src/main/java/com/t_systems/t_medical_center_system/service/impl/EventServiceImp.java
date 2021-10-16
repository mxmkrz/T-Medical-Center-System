package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.entity.EventTime;
import com.t_systems.t_medical_center_system.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class EventServiceImp {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImp(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public void generateEvents(AppointmentDto appointmentDto) {
        countDataAndTime(appointmentDto);





    }


    public List<Date> getDataBetweenStartEndData(AppointmentDto appointmentDto) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(appointmentDto.getStartOfData());

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(appointmentDto.getEndOfData());

        while (calendar.before(endCalendar)) {
            Date result = new Date(calendar.getTime().getTime());
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
    }


    public Map<Date, List<String>> countDataAndTime(AppointmentDto appointmentDto) {
        List<Date> period = getDataBetweenStartEndData(appointmentDto);
        Calendar calendar = new GregorianCalendar();
        Map<Date, List<String>> dateAndTimeMap = new HashMap<>();


        if (appointmentDto.isSunday()) {
            for (Date d : period) {
                calendar.setTime(d);
                List<String> times = new ArrayList<>();
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    listOfAcceptedTimes(appointmentDto, times);
                }
                if (!times.isEmpty()) {
                    dateAndTimeMap.put(d, times);
                }
            }
        }
        if (appointmentDto.isMonday()) {
            for (Date d : period) {
                calendar.setTime(d);
                List<String> times = new ArrayList<>();
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                    listOfAcceptedTimes(appointmentDto, times);
                }
                if (!times.isEmpty()) {
                    dateAndTimeMap.put(d, times);
                }
            }
        }
        if (appointmentDto.isTuesday()) {
            for (Date d : period) {
                calendar.setTime(d);
                List<String> times = new ArrayList<>();
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                    listOfAcceptedTimes(appointmentDto, times);
                }
                if (!times.isEmpty()) {
                    dateAndTimeMap.put(d, times);
                }
            }
        }
        if (appointmentDto.isWednesday()) {
            for (Date d : period) {
                calendar.setTime(d);
                List<String> times = new ArrayList<>();
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                    listOfAcceptedTimes(appointmentDto, times);
                }
                if (!times.isEmpty()) {
                    dateAndTimeMap.put(d, times);
                }
            }
        }
        if (appointmentDto.isThursday()) {
            for (Date d : period) {
                calendar.setTime(d);
                List<String> times = new ArrayList<>();
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                    listOfAcceptedTimes(appointmentDto, times);
                }
                if (!times.isEmpty()) {
                    dateAndTimeMap.put(d, times);
                }
            }
        }
        if (appointmentDto.isFriday()) {
            for (Date d : period) {
                calendar.setTime(d);
                List<String> times = new ArrayList<>();
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                    listOfAcceptedTimes(appointmentDto, times);
                }
                if (!times.isEmpty()) {
                    dateAndTimeMap.put(d, times);
                }
            }
        }
        if (appointmentDto.isSaturday()) {
            for (Date d : period) {
                calendar.setTime(d);
                List<String> times = new ArrayList<>();
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                    listOfAcceptedTimes(appointmentDto, times);
                }
                if (!times.isEmpty()) {
                    dateAndTimeMap.put(d, times);
                }
            }
        }
        return dateAndTimeMap;

    }

    public void listOfAcceptedTimes(AppointmentDto appointmentDto, List<String> times) {
        for (int i = 0; i < appointmentDto.getTime().size(); i++) {
            if (appointmentDto.getTime().get(i).equals("0")) {
                times.add("9:00 - 10:00");
            }
            if (appointmentDto.getTime().get(i).equals("1")) {
                times.add("10:00 - 11:00");
            }
            if (appointmentDto.getTime().get(i).equals("2")) {
                times.add("11:00 - 12:00");
            }
            if (appointmentDto.getTime().get(i).equals("3")) {
                times.add("12:00 - 13:00");
            }
            if (appointmentDto.getTime().get(i).equals("4")) {
                times.add("13:00 - 14:00");
            }
            if (appointmentDto.getTime().get(i).equals("5")) {
                times.add("14:00 - 15:00");
            }
            if (appointmentDto.getTime().get(i).equals("6")) {
                times.add("15:00 - 16:00");
            }
            if (appointmentDto.getTime().get(i).equals("7")) {
                times.add("16:00 - 17:00");
            }
            if (appointmentDto.getTime().get(i).equals("8")) {
                times.add("17:00 - 18:00");
            }
            if (appointmentDto.getTime().get(i).equals("9")) {
                times.add("18:00 - 19:00");
            }
            if (appointmentDto.getTime().get(i).equals("10")) {
                times.add("19:00 - 20:00");
            }
            if (appointmentDto.getTime().get(i).equals("11")) {
                times.add("20:00 - 21:00");
            }


        }

    }




}
