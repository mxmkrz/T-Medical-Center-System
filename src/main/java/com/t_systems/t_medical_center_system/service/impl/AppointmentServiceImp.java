package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.config.RabbitConfig;
import com.t_systems.t_medical_center_system.entity.Event;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.entity.enums.AppointmentStatus;
import com.t_systems.t_medical_center_system.entity.enums.EventStatus;
import com.t_systems.t_medical_center_system.exception.AppointmentNotFoundException;
import com.t_systems.t_medical_center_system.exception.PatientNotFoundException;
import com.t_systems.t_medical_center_system.mapper.AppointmentMapper;
import com.t_systems.t_medical_center_system.mapper.Convertor;
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
        MedicalStaff medicalStaff = medicalStaffRepository.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
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
        MedicalStaff medicalStaff = medicalStaffRepository.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        appointmentEntity.setStaff(medicalStaff);
        eventServiceImp.deleteEvent(appointmentEntity.getId());
        appointmentRepository.save(appointmentEntity);
        eventServiceImp.generateEvents(appointmentDto, idPatient, appointmentEntity.getId());
        log.info("Update appointment");
        rabbitSender.sendMessage("update");

    }

    @Transactional
    @Override
    public void cancelAppointment(AppointmentDto appointmentDto, Long idPatient) {
        if (appointmentDto.getStatus().equals(AppointmentStatus.FINISHED)) {
            List<Event> events = eventServiceImp.findAllByAppointmentId(appointmentDto.getId());

            for (Event e : events) {
                if (!e.getStatus().equals(EventStatus.DONE)) {
                    e.setStatus(EventStatus.CANCELED);
                    e.setReasonToCancel("Doctor canceled");
                    eventServiceImp.updateEvent(e, appointmentDto.getId());
                    rabbitSender.sendMessage("done");
                }
            }
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


}
