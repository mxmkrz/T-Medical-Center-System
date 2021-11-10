package com.t_systems.t_medical_center_system.mapper;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.entity.*;
import com.t_systems.t_medical_center_system.entity.WeekDay;
import com.t_systems.t_medical_center_system.entity.enums.TherapyType;
import com.t_systems.t_medical_center_system.exception.AppointmentNotFoundException;
import com.t_systems.t_medical_center_system.repository.*;
import com.t_systems.t_medical_center_system.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Component
public class AppointmentMapper {
    private final AppointmentRepository appointmentRepository;
    private final ProcedureRepository procedureRepository;
    private final WeekDayRepository weekDayRepository;
    private final EventTimeRepository eventTimeRepository;
    private final DrugRepository drugRepository;
    private final EventTimeServiceImp eventTimeService;
    private final WeekDayServiceImp weekDayService;
    private final DrugServiceImpl drugService;
    private final ProcedureServiceImpl procedureService;

    @Autowired
    public AppointmentMapper(AppointmentRepository appointmentRepository, ProcedureRepository procedureRepository, WeekDayRepository weekDayRepository, EventTimeRepository eventTimeRepository, DrugRepository drugRepository, EventTimeServiceImp eventTimeService, WeekDayServiceImp weekDayService, DrugServiceImpl drugService, ProcedureServiceImpl procedureService) {
        this.appointmentRepository = appointmentRepository;
        this.procedureRepository = procedureRepository;
        this.weekDayRepository = weekDayRepository;
        this.eventTimeRepository = eventTimeRepository;
        this.drugRepository = drugRepository;
        this.eventTimeService = eventTimeService;
        this.weekDayService = weekDayService;
        this.drugService = drugService;
        this.procedureService = procedureService;

    }


    public Appointment toEntity(AppointmentDto appointmentDto) {

        Appointment appointment;
        if (appointmentDto.getId() == null) {
            appointment = new Appointment();
            appointment.setId(appointmentDto.getId());
            appointment.setTherapyType(appointmentDto.getType());
            appointment.setStartDate(appointmentDto.getStartOfData());
            appointment.setEndDate(appointmentDto.getEndOfData());
            appointment.setWeekDay(convertWeekDay(appointmentDto, appointment));
            appointment.setProcedureList(convertProcedure(appointmentDto, appointment));
            appointment.setDrugsList(convertDrugs(appointmentDto, appointment));
            appointment.setTimePatterns(convertEventTime(appointmentDto, appointment));
        } else {

            appointment = appointmentRepository.findById(appointmentDto.getId()).orElseThrow(AppointmentNotFoundException::new);

            List<WeekDay> weekDaysEntity = weekDayRepository.findAllByAppointmentId(appointment.getId());
            weekDayService.delete(weekDaysEntity);

            List<EventTime> timePatternsEntity = eventTimeRepository.findAllByAppointmentId(appointment.getId());
            eventTimeService.delete(timePatternsEntity);

            List<Drug> drugs = drugRepository.findAllByAppointmentId(appointment.getId());
            drugService.deleteDrugs(drugs);

            List<Procedure> proceduresEntity = procedureRepository.findProcedureByAppointmentId(appointment.getId());
            procedureService.delete(proceduresEntity);

            appointment.getWeekDay().addAll(convertWeekDay(appointmentDto, appointment));
            appointment.getProcedureList().addAll(convertProcedure(appointmentDto, appointment));
            appointment.getDrugsList().addAll(convertDrugs(appointmentDto, appointment));
            appointment.getTimePatterns().addAll(convertEventTime(appointmentDto, appointment));
            appointment.setTherapyType(appointmentDto.getType());
            appointment.setStartDate(appointmentDto.getStartOfData());
            appointment.setEndDate(appointmentDto.getEndOfData());

        }
        return appointment;

    }


    public AppointmentDto toDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());

        if (appointment.getTherapyType().equals(TherapyType.PROCEDURE)) {
            appointmentDto.setType(TherapyType.PROCEDURE);
            List<Procedure> procedures = appointment.getProcedureList();
            for (Procedure p : procedures) {
                if (p.getName() != null) {
                    appointmentDto.setInfo(p.getName());
                }
            }

        } else {
            appointmentDto.setType(TherapyType.DRUG);
            List<Drug> drugs = appointment.getDrugsList();
            for (Drug d : drugs) {
                if (d.getName() != null || d.getDosage() != 0) {
                    appointmentDto.setInfoDrugs(d.getName());
                    appointmentDto.setDose(d.getDosage());
                }
            }
        }

        List<WeekDay> weekDay = appointment.getWeekDay();
        List<String> weekDayString = new ArrayList<>();
        for (WeekDay w : weekDay) {
            if (w.getDay() != null) {
                weekDayString.add(w.getDay());
            }
        }
        appointmentDto.setWeekDayString(weekDayString);

        List<EventTime> eventTimes = appointment.getTimePatterns();
        List<String> eventTimesString = new ArrayList<>();
        for (EventTime eve : eventTimes) {
            if (eve.getTime() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                eventTimesString.add(eve.getTime().format(formatter));
            }

        }
        appointmentDto.setEventTimes(eventTimesString);
        appointmentDto.setStatus(appointment.getStatus());
        appointmentDto.setStartOfData(appointment.getStartDate());
        appointmentDto.setEndOfData(appointment.getEndDate());

        return appointmentDto;
    }

    private List<Drug> convertDrugs(AppointmentDto appointmentDto, Appointment appointment) {
        List<Drug> drugs = new ArrayList<>();
        if (Objects.isNull(appointmentDto.getInfo()) || !appointmentDto.getType().equals(TherapyType.DRUG)) {
            drugs.add(null);
        } else {
            Drug drug = new Drug(appointmentDto.getInfoDrugs(), appointmentDto.getDose());
            drug.setAppointment(appointment);
            drugs.add(drug);
        }
        return drugs;

    }


    private List<Procedure> convertProcedure(AppointmentDto appointmentDto, Appointment appointment) {
        List<Procedure> procedures = new ArrayList<>();
        if (Objects.isNull(appointmentDto.getInfo()) || !appointmentDto.getType().equals(TherapyType.PROCEDURE)) {
            procedures.add(null);
        } else {
            Procedure procedure = new Procedure(appointmentDto.getInfo());
            procedure.setAppointment(appointment);
            procedures.add(procedure);
        }
        return procedures;
    }


    private List<EventTime> convertEventTime(AppointmentDto appointmentDto, Appointment appointment) {
        List<EventTime> timePatterns = new ArrayList<>();
        for (int i = 0; i < appointmentDto.getTime().size(); i++) {
            switch (appointmentDto.getTime().get(i)) {
                case ("0") -> timePatterns.add(eventTimeMaker(appointment, 9));
                case ("1") -> timePatterns.add(eventTimeMaker(appointment, 10));
                case ("2") -> timePatterns.add(eventTimeMaker(appointment, 11));
                case ("3") -> timePatterns.add(eventTimeMaker(appointment, 12));
                case ("4") -> timePatterns.add(eventTimeMaker(appointment, 13));
                case ("5") -> timePatterns.add(eventTimeMaker(appointment, 14));
                case ("6") -> timePatterns.add(eventTimeMaker(appointment, 15));
                case ("7") -> timePatterns.add(eventTimeMaker(appointment, 16));
                case ("8") -> timePatterns.add(eventTimeMaker(appointment, 17));
                case ("9") -> timePatterns.add(eventTimeMaker(appointment, 18));
                case ("10") -> timePatterns.add(eventTimeMaker(appointment, 19));
                case ("11") -> timePatterns.add(eventTimeMaker(appointment, 20));
            }
        }
        return timePatterns;
    }

    private EventTime eventTimeMaker(Appointment appointment, int hours) {
        EventTime eventTime = new EventTime();
        eventTime.setAppointment(appointment);
        eventTime.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), hours, 0));
        return eventTime;
    }


    private List<WeekDay> convertWeekDay(AppointmentDto appointmentDto, Appointment appointment) {
        List<WeekDay> weekDays = new ArrayList<>();
        if (appointmentDto.isSunday()) {
            WeekDay sunday = new WeekDay("Sunday");
            sunday.setAppointment(appointment);
            weekDays.add(sunday);
        }
        if (appointmentDto.isMonday()) {
            WeekDay monday = new WeekDay("Monday");
            monday.setAppointment(appointment);
            weekDays.add(monday);
        }
        if (appointmentDto.isTuesday()) {
            WeekDay tuesday = new WeekDay("Tuesday");
            tuesday.setAppointment(appointment);
            weekDays.add(tuesday);
        }
        if (appointmentDto.isWednesday()) {
            WeekDay wednesday = new WeekDay("Wednesday");
            wednesday.setAppointment(appointment);
            weekDays.add(wednesday);
        }
        if (appointmentDto.isThursday()) {
            WeekDay thursday = new WeekDay("Thursday");
            thursday.setAppointment(appointment);
            weekDays.add(thursday);
        }
        if (appointmentDto.isFriday()) {
            WeekDay friday = new WeekDay("Friday");
            friday.setAppointment(appointment);
            weekDays.add(friday);
        }
        if (appointmentDto.isSaturday()) {
            WeekDay saturday = new WeekDay("Saturday");
            saturday.setAppointment(appointment);
            weekDays.add(saturday);
        }
        return weekDays;
    }
}


