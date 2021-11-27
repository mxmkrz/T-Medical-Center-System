package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.entity.Event;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.entity.enums.AppointmentStatus;
import com.t_systems.t_medical_center_system.entity.enums.EventStatus;
import com.t_systems.t_medical_center_system.exception.AppointmentNotFoundException;
import com.t_systems.t_medical_center_system.exception.PatientNotFoundException;
import com.t_systems.t_medical_center_system.mapper.AppointmentMapper;
import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.repository.MedicalStaffRepository;
import com.t_systems.t_medical_center_system.repository.PatientRepository;
import com.t_systems.t_medical_center_system.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class AppointmentServiceImp implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final AppointmentMapper appointmentMapper;
    private final EventServiceImp eventServiceImp;
    private final RabbitSender rabbitSender;
    private final MedicalStaffRepository medicalStaffRepository;

    @Autowired
    public AppointmentServiceImp(AppointmentRepository appointmentRepository, PatientRepository patientRepository, AppointmentMapper appointmentMapper, EventServiceImp eventServiceImp, RabbitSender rabbitSender, MedicalStaffRepository medicalStaffRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.appointmentMapper = appointmentMapper;
        this.eventServiceImp = eventServiceImp;
        this.rabbitSender = rabbitSender;
        this.medicalStaffRepository = medicalStaffRepository;
    }


    @Transactional
    @Override
    public void makeAnAppointment(AppointmentDto appointment, Long id) {
        Appointment appointmentEntity = appointmentMapper.toEntity(appointment);
        Patient patient = patientRepository.findById(id).orElseThrow(PatientNotFoundException::new);
        MedicalStaff medicalStaff = medicalStaffRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        appointmentEntity.setStaff(medicalStaff);
        appointmentEntity.setPatient(patient);
        appointmentEntity.setStatus(AppointmentStatus.ACTIVE);
        appointmentRepository.save(appointmentEntity);
        eventServiceImp.generateEvents(appointment, id, appointmentEntity.getId());
        log.info("Create appointment");
        rabbitSender.sendMessage("create");
    }

    @Transactional(readOnly = true)
    @Override
    public Page<AppointmentDto> getAppointmentListByPatientId(Long id, Pageable pageable) {
        checkStatusEvents(id);
        Patient patient = patientRepository.findById(id).orElseThrow(PatientNotFoundException::new);
        return appointmentRepository.findAllByPatient(patient, pageable).map(appointmentMapper::toDto);
    }

    @Transactional(readOnly = true)
    @Override
    public AppointmentDto getAppointById(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(AppointmentNotFoundException::new);
        return appointmentMapper.toDto(appointment);
    }

    @Transactional
    @Override
    public void updateAppointment(AppointmentDto appointmentDto, Long idPatient) {
        Appointment appointmentEntity = appointmentRepository.findById(appointmentDto.getId()).orElseThrow(AppointmentNotFoundException::new);
        appointmentEntity = appointmentMapper.toEntity(appointmentDto);
        Patient patient = patientRepository.findById(idPatient).orElseThrow(PatientNotFoundException::new);
        appointmentEntity.setPatient(patient);
        MedicalStaff medicalStaff = medicalStaffRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        appointmentEntity.setStaff(medicalStaff);
        eventServiceImp.deleteEvent(appointmentEntity.getId());
        appointmentRepository.save(appointmentEntity);
        eventServiceImp.generateEvents(appointmentDto, idPatient, appointmentEntity.getId());
        log.info("Update appointment");
        rabbitSender.sendMessage("update");

    }

    /**
     * Cancel or done an appointment.Set status for events.
     *
     * @param appointmentDto the appointmentDto
     * @param idPatient      the idPatient
     */
    @Transactional
    @Override
    public void cancelOrDoneAppointment(AppointmentDto appointmentDto, Long idPatient) {
        if (appointmentDto.getStatus().equals(AppointmentStatus.FINISHED)) {
            List<Event> events = eventServiceImp.findAllByAppointmentId(appointmentDto.getId());

            for (Event e : events) {
                if (!e.getStatus().equals(EventStatus.DONE)) {
                    e.setStatus(EventStatus.CANCELED);
                    e.setReasonToCancel("Doctor canceled");
                    eventServiceImp.updateEvent(e, appointmentDto.getId());
                }
            }
            rabbitSender.sendMessage("done");
            Appointment appointment = appointmentRepository.findById(appointmentDto.getId()).orElseThrow(AppointmentNotFoundException::new);
            appointment.setStatus(AppointmentStatus.FINISHED);
            appointmentRepository.save(appointment);
            log.info("Appointment canceled and event statuses canceled");

        } else if (appointmentDto.getStatus().equals(AppointmentStatus.DONE)) {
            List<Event> events = eventServiceImp.findAllByAppointmentId(appointmentDto.getId());

            for (Event e : events) {
                if (!e.getStatus().equals(EventStatus.DONE)) {
                    e.setStatus(EventStatus.DONE);
                    e.setReasonToCancel("Doctor completed the appointment");
                    eventServiceImp.updateEvent(e, appointmentDto.getId());
                }
            }
            Appointment appointment = appointmentRepository.findById(appointmentDto.getId()).orElseThrow(AppointmentNotFoundException::new);
            appointment.setStatus(AppointmentStatus.DONE);
            appointmentRepository.save(appointment);
            rabbitSender.sendMessage("cancel");
            log.info("Appointment completed and event statuses completed ");
        }
    }

    /**
     * This is the method necessary for setting appointment status if the nurse cancels or done or cancels and done all events.
     * The method calculates event status if events equal list size next set appointment status.
     *
     * @param id the id patient
     */
    @Override
    @Transactional
    public void checkStatusEvents(Long id) {
        List<Appointment> appointments = appointmentRepository.findAllByPatientId(id);
        int countEventDone = 0;
        int countEventCancel = 0;
        for (Appointment a : appointments) {
            List<Event> events = eventServiceImp.findAllByAppointmentId(a.getId());
            for (Event e : events) {
                if (e.getStatus().equals(EventStatus.DONE)) {
                    countEventDone++;
                }
                if (e.getStatus().equals(EventStatus.CANCELED)) {
                    countEventCancel++;
                }
            }
            if (countEventDone == events.size()) {
                a.setStatus(AppointmentStatus.DONE);
                countEventDone = 0;
            }
            if (countEventCancel == events.size()) {
                a.setStatus(AppointmentStatus.FINISHED);
                countEventCancel = 0;
            }
            if (countEventCancel + countEventDone == events.size()) {
                a.setStatus(AppointmentStatus.FINISHED);
                countEventDone = 0;
                countEventCancel = 0;
            }

        }

    }


}
