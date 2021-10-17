package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.dto.EventDto;
import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.Event;
import com.t_systems.t_medical_center_system.entity.EventTime;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.entity.enums.EventStatus;
import com.t_systems.t_medical_center_system.exception.AppointmentNotFoundException;
import com.t_systems.t_medical_center_system.exception.PatientNotFoundException;
import com.t_systems.t_medical_center_system.mapper.AppointmentMapper;
import com.t_systems.t_medical_center_system.mapper.EventMapper;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.repository.EventRepository;
import com.t_systems.t_medical_center_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class EventServiceImp {

    private final EventRepository eventRepository;
    private final PatientRepository patientRepository;
    private final AppointmentMapper appointmentMapper;
    private final AppointmentServiceImp appointmentServiceImp;
    private final EventMapper eventMapper;

    @Autowired
    public EventServiceImp(EventRepository eventRepository, PatientRepository patientRepository, AppointmentMapper appointmentMapper, AppointmentServiceImp appointmentServiceImp, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.patientRepository = patientRepository;
        this.appointmentMapper = appointmentMapper;
        this.appointmentServiceImp = appointmentServiceImp;
        this.eventMapper = eventMapper;
    }


    @Transactional
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }


    @Transactional
    public void generateEvents(AppointmentDto appointmentDto, Long id) {
        Map<Date, List<LocalTime>> dataAndTimes = countDataAndTime(appointmentDto);
        System.out.println(appointmentDto.getId());

        Appointment appointment = appointmentMapper.toEntity(appointmentDto);
        Long idAppointment = appointmentServiceImp.getSavedId(appointment);
        appointment.setId(idAppointment);


        Patient patient = patientRepository.findById(id).orElseThrow(PatientNotFoundException::new);


        for (Map.Entry<Date, List<LocalTime>> dt : dataAndTimes.entrySet()) {

            for (LocalTime time : dt.getValue()) {
                Event event = new Event(dt.getKey(),
                        time,
                        EventStatus.PLANNED,
                        appointmentDto.getType(),
                        appointment,
                        patient);
                saveEvent(event);
            }
        }
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


    public Map<Date, List<LocalTime>> countDataAndTime(AppointmentDto appointmentDto) {
        List<Date> period = getDataBetweenStartEndData(appointmentDto);
        Calendar calendar = new GregorianCalendar();
        Map<Date, List<LocalTime>> dateAndTimeMap = new HashMap<>();


        if (appointmentDto.isSunday()) {
            for (Date d : period) {
                calendar.setTime(d);
                List<LocalTime> times = new ArrayList<>();
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
                List<LocalTime> times = new ArrayList<>();
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
                List<LocalTime> times = new ArrayList<>();
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
                List<LocalTime> times = new ArrayList<>();
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
                List<LocalTime> times = new ArrayList<>();
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
                List<LocalTime> times = new ArrayList<>();
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
                List<LocalTime> times = new ArrayList<>();
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

    public void listOfAcceptedTimes(AppointmentDto appointmentDto, List<LocalTime> times) {
        for (int i = 0; i < appointmentDto.getTime().size(); i++) {
            if (appointmentDto.getTime().get(i).equals("0")) {
                times.add(LocalTime.of(9, 0));


            }
            if (appointmentDto.getTime().get(i).equals("1")) {
                times.add(LocalTime.of(10, 0));
            }
            if (appointmentDto.getTime().get(i).equals("2")) {
                times.add(LocalTime.of(11, 0));
            }
            if (appointmentDto.getTime().get(i).equals("3")) {
                times.add(LocalTime.of(12, 0));
            }
            if (appointmentDto.getTime().get(i).equals("4")) {
                times.add(LocalTime.of(13, 0));
            }
            if (appointmentDto.getTime().get(i).equals("5")) {
                times.add(LocalTime.of(14, 0));
            }
            if (appointmentDto.getTime().get(i).equals("6")) {
                times.add(LocalTime.of(15, 0));
            }
            if (appointmentDto.getTime().get(i).equals("7")) {
                times.add(LocalTime.of(16, 0));
            }
            if (appointmentDto.getTime().get(i).equals("8")) {
                times.add(LocalTime.of(17, 0));
            }
            if (appointmentDto.getTime().get(i).equals("9")) {
                times.add(LocalTime.of(18, 0));
            }
            if (appointmentDto.getTime().get(i).equals("10")) {
                times.add(LocalTime.of(19, 0));
            }
            if (appointmentDto.getTime().get(i).equals("11")) {
                times.add(LocalTime.of(20, 0));
            }


        }

    }

    @Transactional
    public List<EventDto> findAllEvents() {
        List<Event> result = (List<Event>) eventRepository.findAll();
        return eventMapper.toDtoList(result);


    }


    @Transactional
    public List<EventDto> findAllEventsByPatients(Long id) {
        List<Event> result = (List<Event>) eventRepository.findAllByPatientId(id);
        return eventMapper.toDtoList(result);


    }
    @Transactional
    public List<EventDto> findAllEventsForHour() {
        LocalTime current = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        LocalTime currentPlusHour = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).plusHours(1);
        LocalDate now = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        now = now.plusMonths(1);
        Date date = Date.valueOf(now);

        List<Event> result = eventRepository.findAllForHour(currentPlusHour,current,date);
        return eventMapper.toDtoList(result);
    }
    @Transactional
    public List<EventDto> findAllEventsForDay() {

        LocalDate now = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        now = now.plusMonths(1);
        Date date = Date.valueOf(now);

        LocalDate plusDay = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        plusDay = plusDay.plusDays(2).plusMonths(1);
        Date date1 = Date.valueOf(plusDay);

        List < Event > result = (List<Event>) eventRepository.findAllForDay(date1,date);
        return eventMapper.toDtoList(result);


    }

    @Transactional
    public List<EventDto> findAllPatientByName(String name) {
        List<Event> result = eventRepository.findAllBy(name);
        return eventMapper.toDtoList(result);


    }


    @Transactional
    public void updateEventStatus(EventStatus eventStatus,Long id) {
        eventRepository.updateStatus(eventStatus,id);
    }









}
