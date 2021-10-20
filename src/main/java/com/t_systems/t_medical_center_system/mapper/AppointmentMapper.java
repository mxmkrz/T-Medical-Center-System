package com.t_systems.t_medical_center_system.mapper;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.entity.*;
import com.t_systems.t_medical_center_system.entity.WeekDay;
import com.t_systems.t_medical_center_system.entity.enums.TherapyType;
import com.t_systems.t_medical_center_system.exception.AppointmentNotFoundException;
import com.t_systems.t_medical_center_system.repository.*;
import com.t_systems.t_medical_center_system.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Component
public class AppointmentMapper {


    private PatientRepository patientRepository;
    private AppointmentRepository appointmentRepository;
    private ProcedureRepository procedureRepository;
    private MedicalStaffRepository medicalStaffRepository;
    private WeekDayRepository weekDayRepository;
    private EventTimeRepository eventTimeRepository;
    private DrugRepository drugRepository;
    private EventRepository eventRepository;
    private EventTimeService eventTimeService;
    private WeekDayService weekDayService;
    private DrugServiceImpl drugService;
    private ProcedureServiceImpl procedureService;

    @Autowired
    public AppointmentMapper(PatientRepository patientRepository, AppointmentRepository appointmentRepository, ProcedureRepository procedureRepository, MedicalStaffRepository medicalStaffRepository, WeekDayRepository weekDayRepository, EventTimeRepository eventTimeRepository, DrugRepository drugRepository, EventRepository eventRepository, EventTimeService eventTimeService, WeekDayService weekDayService, DrugServiceImpl drugService, ProcedureServiceImpl procedureService) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.procedureRepository = procedureRepository;
        this.medicalStaffRepository = medicalStaffRepository;
        this.weekDayRepository = weekDayRepository;
        this.eventTimeRepository = eventTimeRepository;
        this.drugRepository = drugRepository;
        this.eventRepository = eventRepository;
        this.eventTimeService = eventTimeService;
        this.weekDayService = weekDayService;
        this.drugService = drugService;
        this.procedureService = procedureService;

    }











    public Appointment toEntity(AppointmentDto appointmentDto) {


        if (appointmentDto.getId() == null) {
            Appointment appointment = new Appointment();
            appointment.setId(appointmentDto.getId());
            appointment.setTherapyType(appointmentDto.getType());
            appointment.setStartDate(appointmentDto.getStartOfData());
            appointment.setEndDate(appointmentDto.getEndOfData());


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

            appointment.setWeekDay(weekDays);


            List<Procedure> procedures = new ArrayList<>();
            if (Objects.isNull(appointmentDto.getInfo()) || !appointmentDto.getType().equals(TherapyType.PROCEDURE)) {
                procedures.add(null);
            } else {
                Procedure procedure = new Procedure(appointmentDto.getInfo());
                procedure.setAppointment(appointment);
                procedures.add(procedure);
            }
            appointment.setProcedureList(procedures);

            List<Drug> drugs = new ArrayList<>();
            if (Objects.isNull(appointmentDto.getInfo()) || !appointmentDto.getType().equals(TherapyType.DRUG)) {
                drugs.add(null);
            } else {
                Drug drug = new Drug(appointmentDto.getInfoDrugs(), appointmentDto.getDose());
                drug.setAppointment(appointment);
                drugs.add(drug);

            }
            appointment.setDrugsList(drugs);

            List<EventTime> timePatterns = new ArrayList<>();
            for (int i = 0; i < appointmentDto.getTime().size(); i++) {
//            switch (appointmentDto.getTime().get(i)) {
//                case ("0"):
//                    timeHandling(appointment, 9);
//                    break;
//                case ("1"):
//                    timeHandling(appointment,10);
//                    break;
//            }
                if (appointmentDto.getTime().get(i).equals("0")) {
                    EventTime tenOClock = new EventTime();
                    tenOClock.setAppointment(appointment);
                    tenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 9, 0));
                    timePatterns.add(tenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("1")) {
                    EventTime tenOClock = new EventTime();
                    tenOClock.setAppointment(appointment);
                    tenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 10, 0));
                    timePatterns.add(tenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("2")) {
                    EventTime elevenOClock = new EventTime();
                    elevenOClock.setAppointment(appointment);
                    elevenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 11, 0));
                    timePatterns.add(elevenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("3")) {
                    EventTime twelveOClock = new EventTime();
                    twelveOClock.setAppointment(appointment);
                    twelveOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 12, 0));
                    timePatterns.add(twelveOClock);
                }
                if (appointmentDto.getTime().get(i).equals("4")) {
                    EventTime thirteenOClock = new EventTime();
                    thirteenOClock.setAppointment(appointment);
                    thirteenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 13, 0));
                    timePatterns.add(thirteenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("5")) {
                    EventTime fourteenOClock = new EventTime();
                    fourteenOClock.setAppointment(appointment);
                    fourteenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 14, 0));
                    timePatterns.add(fourteenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("6")) {
                    EventTime fifteenOClock = new EventTime();
                    fifteenOClock.setAppointment(appointment);
                    fifteenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 15, 0));
                    timePatterns.add(fifteenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("7")) {
                    EventTime sixteenOClock = new EventTime();
                    sixteenOClock.setAppointment(appointment);
                    sixteenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 16, 0));
                    timePatterns.add(sixteenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("8")) {
                    EventTime seventeenOClock = new EventTime();
                    seventeenOClock.setAppointment(appointment);
                    seventeenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 17, 0));
                    timePatterns.add(seventeenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("9")) {
                    EventTime eighteenOClock = new EventTime();
                    eighteenOClock.setAppointment(appointment);
                    eighteenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 18, 0));
                    timePatterns.add(eighteenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("10")) {
                    EventTime nineteenOClock = new EventTime();
                    nineteenOClock.setAppointment(appointment);
                    nineteenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 19, 0));
                    timePatterns.add(nineteenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("11")) {
                    EventTime twentyOClock = new EventTime();
                    twentyOClock.setAppointment(appointment);
                    twentyOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 20, 0));
                    timePatterns.add(twentyOClock);
                }

            }

            appointment.setTimePatterns(timePatterns);

            MedicalStaff medicalStaff = medicalStaffRepository.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
            appointment.setStaff(medicalStaff);
            return appointment;

        } else {

            Appointment appointment = appointmentRepository.findById(appointmentDto.getId()).orElseThrow(AppointmentNotFoundException::new);
//            List<Event> events = eventRepository.findAllByAppointmentId(appointment.getId());
//            eventRepository.deleteAll(events);

            List<WeekDay> weekDaysEntity = weekDayRepository.findAllByAppointmentId(appointment.getId());
            weekDayService.delete(weekDaysEntity);
            List<WeekDay> weekDays = new ArrayList<>();

            List<EventTime> timePatternsEntity = eventTimeRepository.findAllByAppointmentId(appointment.getId());
            eventTimeService.delete(timePatternsEntity);
            List<EventTime> timePatterns = new ArrayList<>();

            List<Drug> drugs = drugRepository.findAllByAppointmentId(appointment.getId());
            drugService.delete(drugs);

            List<Procedure> proceduresEntity = procedureRepository.findProcedureByAppointmentId(appointment.getId());
            procedureService.delete(proceduresEntity);



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


            appointment.getWeekDay().addAll(weekDays);


            List<Procedure> procedures = new ArrayList<>();
            if (Objects.isNull(appointmentDto.getInfo()) || !appointmentDto.getType().equals(TherapyType.PROCEDURE)) {
                procedures.add(null);
            } else {
                Procedure procedure = new Procedure(appointmentDto.getInfo());
                procedure.setAppointment(appointment);
                procedures.add(procedure);
            }
            appointment.getProcedureList().addAll(procedures);

            List<Drug> drugsNew = new ArrayList<>();
            if (Objects.isNull(appointmentDto.getInfo()) || !appointmentDto.getType().equals(TherapyType.DRUG)) {
                drugsNew.add(null);
            } else {
                Drug drug = new Drug(appointmentDto.getInfoDrugs(), appointmentDto.getDose());
                drug.setAppointment(appointment);
                drugsNew.add(drug);

            }
            appointment.getDrugsList().addAll(drugsNew);




            for (int i = 0; i < appointmentDto.getTime().size(); i++) {
//            switch (appointmentDto.getTime().get(i)) {
//                case ("0"):
//                    timeHandling(appointment, 9);
//                    break;
//                case ("1"):
//                    timeHandling(appointment,10);
//                    break;
//            }
                if (appointmentDto.getTime().get(i).equals("0")) {
                    EventTime tenOClock = new EventTime();
                    tenOClock.setAppointment(appointment);
                    tenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 9, 0));
                    timePatterns.add(tenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("1")) {
                    EventTime tenOClock = new EventTime();
                    tenOClock.setAppointment(appointment);
                    tenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 10, 0));
                    timePatterns.add(tenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("2")) {
                    EventTime elevenOClock = new EventTime();
                    elevenOClock.setAppointment(appointment);
                    elevenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 11, 0));
                    timePatterns.add(elevenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("3")) {
                    EventTime twelveOClock = new EventTime();
                    twelveOClock.setAppointment(appointment);
                    twelveOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 12, 0));
                    timePatterns.add(twelveOClock);
                }
                if (appointmentDto.getTime().get(i).equals("4")) {
                    EventTime thirteenOClock = new EventTime();
                    thirteenOClock.setAppointment(appointment);
                    thirteenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 13, 0));
                    timePatterns.add(thirteenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("5")) {
                    EventTime fourteenOClock = new EventTime();
                    fourteenOClock.setAppointment(appointment);
                    fourteenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 14, 0));
                    timePatterns.add(fourteenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("6")) {
                    EventTime fifteenOClock = new EventTime();
                    fifteenOClock.setAppointment(appointment);
                    fifteenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 15, 0));
                    timePatterns.add(fifteenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("7")) {
                    EventTime sixteenOClock = new EventTime();
                    sixteenOClock.setAppointment(appointment);
                    sixteenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 16, 0));
                    timePatterns.add(sixteenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("8")) {
                    EventTime seventeenOClock = new EventTime();
                    seventeenOClock.setAppointment(appointment);
                    seventeenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 17, 0));
                    timePatterns.add(seventeenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("9")) {
                    EventTime eighteenOClock = new EventTime();
                    eighteenOClock.setAppointment(appointment);
                    eighteenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 18, 0));
                    timePatterns.add(eighteenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("10")) {
                    EventTime nineteenOClock = new EventTime();
                    nineteenOClock.setAppointment(appointment);
                    nineteenOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 19, 0));
                    timePatterns.add(nineteenOClock);
                }
                if (appointmentDto.getTime().get(i).equals("11")) {
                    EventTime twentyOClock = new EventTime();
                    twentyOClock.setAppointment(appointment);
                    twentyOClock.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), 20, 0));
                    timePatterns.add(twentyOClock);
                }

            }


            appointment.getTimePatterns().addAll(timePatterns);

            MedicalStaff medicalStaff = medicalStaffRepository.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
            appointment.setStaff(medicalStaff);



            appointment.setTherapyType(appointmentDto.getType());
            appointment.setStartDate(appointmentDto.getStartOfData());
            appointment.setEndDate(appointmentDto.getEndOfData());

//
//            List<Procedure> procedureListFromEntity = procedureRepository.findProcedureByAppointmentId(appointment.getId());
//            appointment.setProcedureList(procedureListFromEntity);
//
//            List<Drug> drugListFromEntity = drugRepository.findAllByAppointmentId(appointment.getId());
//            appointment.setDrugsList(drugListFromEntity);

//
//            appointment.setTherapyType(appointmentDto.getType());
//            appointment.setStartDate(appointmentDto.getStartOfData());
//            appointment.setEndDate(appointmentDto.getEndOfData());



            return appointment;
        }

    }


//    private void timeHandling(Appointment appointment, int hours) {
//        EventTime eventTime = new EventTime();
//        eventTime.setAppointment(appointment);
//        eventTime.setTime(LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), hours, 0));
//        timePatterns.add(eventTime);
//    }


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

        appointmentDto.setStartOfData(appointment.getStartDate());
        appointmentDto.setEndOfData(appointment.getEndDate());

        return appointmentDto;
    }

    public List<AppointmentDto> toDtoList(List<Appointment> appointments) {
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        for (Appointment a : appointments) {
            appointmentDtos.add(toDto(a));
        }
        return appointmentDtos;
    }


}


