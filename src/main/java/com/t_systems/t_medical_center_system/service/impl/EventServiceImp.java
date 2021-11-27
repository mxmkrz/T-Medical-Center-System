package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.dto.EventDto;
import com.t_systems.t_medical_center_system.dto.EventBoardDto;
import com.t_systems.t_medical_center_system.dto.Filter;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.Event;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.entity.enums.EventStatus;
import com.t_systems.t_medical_center_system.exception.AppointmentNotFoundException;
import com.t_systems.t_medical_center_system.exception.EventNotFoundException;
import com.t_systems.t_medical_center_system.exception.PatientNotFoundException;
import com.t_systems.t_medical_center_system.mapper.EventMapper;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.repository.EventRepository;
import com.t_systems.t_medical_center_system.repository.PatientRepository;
import com.t_systems.t_medical_center_system.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Slf4j
@Service
public class EventServiceImp implements EventService {

    private final EventRepository eventRepository;
    private final PatientRepository patientRepository;
    private final EventMapper eventMapper;
    private final AppointmentRepository appointmentRepository;
    private final RabbitSender rabbitSender;


    @Autowired
    public EventServiceImp(EventRepository eventRepository, PatientRepository patientRepository, EventMapper eventMapper, AppointmentRepository appointmentRepository, RabbitSender rabbitSender) {
        this.eventRepository = eventRepository;
        this.patientRepository = patientRepository;
        this.eventMapper = eventMapper;
        this.appointmentRepository = appointmentRepository;
        this.rabbitSender = rabbitSender;
    }

    @Override
    @Transactional
    public void saveEvent(Event event) {
        eventRepository.save(event);
        log.info("Event saved");
    }

    @Override
    @Transactional
    public void deleteEvent(Long id) {
        List<Event> events = eventRepository.findAllByAppointmentId(id);
        eventRepository.deleteAll(events);
        log.info("Delete event by appointment id");

    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> findAllByAppointmentId(Long id) {
        return eventRepository.findAllByAppointmentId(id);

    }

    /**
     * This is method creates events.Method gets map date with list times.
     * Then go by map and by list with times.One event is created for each time
     *
     * @param appointmentDto the eventDto
     * @param idPatient      the idPatient for find patient
     * @param appointmentId  the appointmentId for find appointment
     */
    @Override
    @Transactional
    public void generateEvents(AppointmentDto appointmentDto, Long idPatient, Long appointmentId) {
        Map<Date, List<LocalTime>> dataAndTimes = countDataAndTime(appointmentDto);
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(AppointmentNotFoundException::new);
        Patient patient = null;
        if (idPatient == 0) {
            patient = new Patient();
            patient.setId(idPatient);
        } else {
            patient = patientRepository.findById(idPatient).orElseThrow(PatientNotFoundException::new);
        }

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

    /**
     * This is method sets event status on cancel or done.
     * Set reason to cancel only for canceled event
     *
     * @param eventDto the eventDto
     */

    @Override
    @Transactional
    public void updateStatus(EventDto eventDto) {
        Event event = eventRepository.findById(eventDto.getId()).orElseThrow(EventNotFoundException::new);
        event = eventMapper.toEntity(eventDto);

        if (eventDto.getStatus().name().equals("CANCELED")) {
            event.setStatus(EventStatus.CANCELED);
            event.setReasonToCancel(eventDto.getReasonToCancel());
            rabbitSender.sendMessage("event canceled");
        } else {
            event.setStatus(EventStatus.DONE);
            rabbitSender.sendMessage("event done");
        }


        Patient patient = patientRepository.findById(eventDto.getIdPatient()).orElseThrow(PatientNotFoundException::new);
        Appointment appointment = appointmentRepository.findById(eventDto.getIdAppointment()).orElseThrow(AppointmentNotFoundException::new);
        event.setPatient(patient);
        event.setAppointment(appointment);
        eventRepository.save(event);
        log.info("Update status event");

    }

    @Override
    @Transactional
    public void updateEvent(Event event, Long idAppointment) {
        Event eventOne = eventRepository.findById(event.getId()).orElseThrow(EventNotFoundException::new);
        Patient patient = patientRepository.findById(eventOne.getPatient().getId()).orElseThrow(PatientNotFoundException::new);
        Appointment appointment = appointmentRepository.findById(idAppointment).orElseThrow(AppointmentNotFoundException::new);
        eventOne.setPatient(patient);
        eventOne.setAppointment(appointment);
        eventRepository.save(eventOne);
        log.info("Update event");
    }

    @Transactional(readOnly = true)
    @Override
    public Page<EventDto> findAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable).map(eventMapper::toDto);
    }

    /**
     * This is method find events  the next hour during current day
     *
     * @param pageable the pageable
     * @return list events for second app during current day
     */
    @Transactional(readOnly = true)
    @Override
    public Page<EventDto> findAllEventsForHour(Pageable pageable) {
        LocalTime current = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        LocalTime currentPlusHour = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).plusHours(1);
        LocalDate day = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        day = day.plusMonths(1);
        Date date = Date.valueOf(day);
        log.info("found list for the current hour");
        return eventRepository.findAllForHour(currentPlusHour, current, date, pageable).map(eventMapper::toDto);
    }

    /**
     * This is method find events between  yesterday's date and tomorrow's date
     *
     * @param pageable the pageable
     * @return list events for second app during current day
     */
    @Transactional(readOnly = true)
    @Override
    public Page<EventDto> findAllEventsForDay(Pageable pageable) {
        LocalDate day = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        day = day.plusMonths(1).minusDays(1);
        Date date = Date.valueOf(day);

        LocalDate plusDay = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        plusDay = plusDay.plusMonths(1).plusDays(1);
        Date date1 = Date.valueOf(plusDay);
        log.info("found list for the current day");
        return eventRepository.findAllForDay(date1, date, pageable).map(eventMapper::toDto);

    }

    @Transactional(readOnly = true)
    @Override
    public Page<EventDto> findAllPatientByName(String name, Pageable pageable) {
        log.info("filter by name");
        return eventRepository.findAllBy(name, pageable).map(eventMapper::toDto);
    }

    /**
     * This is method find events between  yesterday's date and tomorrow's date
     * This is  method use an only a second app
     *
     * @return list events for second app during current day
     */
    @Transactional(readOnly = true)
    @Override
    public List<EventBoardDto> findAllEventsDayForBoard() {
        LocalDate now = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        now = now.plusMonths(1).minusDays(1);
        Date date = Date.valueOf(now);
        LocalDate plusDay = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        plusDay = plusDay.plusMonths(1).plusDays(1);
        Date date1 = Date.valueOf(plusDay);
        List<Event> eventSpringDtos = eventRepository.findAllForDay(date1, date);
        log.info("found a list for the board");
        return eventMapper.toStringDtoList(eventSpringDtos);

    }

    /**
     * This is method for filter information
     *
     * @param filter   the filter number
     * @param keyword  name or surname patient for search
     * @param pageable the pageable
     * @return page by request number filter
     */
    @Transactional
    @Override
    public Page<EventDto> doFilter(Filter filter, String keyword, Pageable pageable) {
        if (filter.getAnInt().equals("0")) return findAllEvents(pageable);
        if (filter.getAnInt().equals("1")) return findAllPatientByName(keyword, pageable);
        if (filter.getAnInt().equals("2")) return findAllEventsForDay(pageable);
        if (filter.getAnInt().equals("3")) return findAllEventsForHour(pageable);
        return null;
    }

    /**
     * This is method finds a list with dates between a start date and end date.
     * Method compare dates with Gregorian Calendar.
     *
     * @param appointmentDto the appointmentDto
     * @return list with dates in the period
     */

    public List<Date> getDataBetweenStartEndData(AppointmentDto appointmentDto) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(appointmentDto.getStartOfData());

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(appointmentDto.getEndOfData());
        endCalendar.add(Calendar.DATE, 1);

        while (calendar.before(endCalendar)) {
            Date result = new Date(calendar.getTime().getTime());
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }

        return datesInRange;
    }

    /**
     * This is method checks dates and times about appointment
     *
     * @param weekDay        the weekday necessary for to set a specific day of the week
     * @param period         the period for go by dates
     * @param calendar       the calendar that compare with real weekday
     * @param dateAndTimeMap the dateAndTimeMap for  fill map
     * @param appointmentDto the appointmentDto for method listOfAcceptedTimes
     */
    private void checkWeekDayAndAddTime(Integer weekDay, List<Date> period, Calendar calendar, Map<Date, List<LocalTime>> dateAndTimeMap, AppointmentDto appointmentDto) {
        for (Date d : period) {
            calendar.setTime(d);
            List<LocalTime> times = new ArrayList<>();
            if (calendar.get(Calendar.DAY_OF_WEEK) == weekDay) {
                listOfAcceptedTimes(appointmentDto, times);
            }
            if (!times.isEmpty()) {
                dateAndTimeMap.put(d, times);
            }
        }
    }

    /**
     * This is method finds count weekday from user
     *
     * @param appointmentDto the appointmentDto for calculate weekday
     * @return map with dates and list times
     */
    public Map<Date, List<LocalTime>> countDataAndTime(AppointmentDto appointmentDto) {
        List<Date> period = getDataBetweenStartEndData(appointmentDto);
        Calendar calendar = new GregorianCalendar();
        Map<Date, List<LocalTime>> dateAndTimeMap = new HashMap<>();


        if (appointmentDto.isSunday()) {
            checkWeekDayAndAddTime(Calendar.SUNDAY, period, calendar, dateAndTimeMap, appointmentDto);
        }
        if (appointmentDto.isMonday()) {
            checkWeekDayAndAddTime(Calendar.MONDAY, period, calendar, dateAndTimeMap, appointmentDto);
        }
        if (appointmentDto.isTuesday()) {
            checkWeekDayAndAddTime(Calendar.TUESDAY, period, calendar, dateAndTimeMap, appointmentDto);
        }
        if (appointmentDto.isWednesday()) {
            checkWeekDayAndAddTime(Calendar.WEDNESDAY, period, calendar, dateAndTimeMap, appointmentDto);
        }
        if (appointmentDto.isThursday()) {
            checkWeekDayAndAddTime(Calendar.THURSDAY, period, calendar, dateAndTimeMap, appointmentDto);
        }
        if (appointmentDto.isFriday()) {
            checkWeekDayAndAddTime(Calendar.FRIDAY, period, calendar, dateAndTimeMap, appointmentDto);
        }
        if (appointmentDto.isSaturday()) {
            checkWeekDayAndAddTime(Calendar.SATURDAY, period, calendar, dateAndTimeMap, appointmentDto);
        }
        return dateAndTimeMap;

    }

    /**
     * This is method finds list times from 9 o'clock to 21 o'clock and install real times.
     *
     * @param appointmentDto the appointmentDto for calculate weekday
     * @param times          the times for calculate times from users
     */
    public void listOfAcceptedTimes(AppointmentDto appointmentDto, List<LocalTime> times) {
        for (int i = 0; i < appointmentDto.getTime().size(); i++) {
            switch (appointmentDto.getTime().get(i)) {
                case ("0") -> times.add(LocalTime.of(9, 0));
                case ("1") -> times.add(LocalTime.of(10, 0));
                case ("2") -> times.add(LocalTime.of(11, 0));
                case ("3") -> times.add(LocalTime.of(12, 0));
                case ("4") -> times.add(LocalTime.of(13, 0));
                case ("5") -> times.add(LocalTime.of(14, 0));
                case ("6") -> times.add(LocalTime.of(15, 0));
                case ("7") -> times.add(LocalTime.of(16, 0));
                case ("8") -> times.add(LocalTime.of(17, 0));
                case ("9") -> times.add(LocalTime.of(18, 0));
                case ("10") -> times.add(LocalTime.of(19, 0));
                case ("11") -> times.add(LocalTime.of(20, 0));
            }
        }

    }


}
