package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.entity.Event;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentServiceImp implements AppointmentService {

    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;
    private Convertor<Appointment, AppointmentDto> convertor;
    private AppointmentMapper appointmentMapper;
    private MedicalStaffRepository medicalStaffRepository;
    private EventServiceImp eventServiceImp;

    @Autowired
    public AppointmentServiceImp(AppointmentRepository appointmentRepository, PatientRepository patientRepository, Convertor<Appointment, AppointmentDto> convertor, AppointmentMapper appointmentMapper, MedicalStaffRepository medicalStaffRepository, EventServiceImp eventServiceImp) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.convertor = convertor;
        this.appointmentMapper = appointmentMapper;
        this.medicalStaffRepository = medicalStaffRepository;
        this.eventServiceImp = eventServiceImp;
    }


    @Transactional
    @Override
    public void addAppointment(AppointmentDto appointment, Long id) {
        Appointment appointmentEntity = appointmentMapper.toEntity(appointment);
        Patient patient = patientRepository.findById(id).orElseThrow(PatientNotFoundException::new);
        appointmentEntity.setPatient(patient);
        appointmentEntity.setStatus(AppointmentStatus.ACTIVE);
        appointmentRepository.save(appointmentEntity);
        eventServiceImp.generateEvents(appointment, id, appointmentEntity.getId());
    }


    @Override
    public List<AppointmentDto> getAppointmentListByPatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(PatientNotFoundException::new);
        List<Appointment> appointments = appointmentRepository.findAllByPatient(patient);
        return appointmentMapper.toDtoList(appointments);
    }

    @Override
    public AppointmentDto gitAppointById(Long id) {
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
        eventServiceImp.deleteEvent(appointmentEntity.getId());
        appointmentRepository.save(appointmentEntity);
        eventServiceImp.generateEvents(appointmentDto, idPatient, appointmentEntity.getId());
    }


    public void cancelAppointment(AppointmentDto appointmentDto) {


        if (appointmentDto.getStatus().equals(AppointmentStatus.CANCELED)) {
            List<Event> events = eventServiceImp.findAllByAppointmentId(appointmentDto.getId());

           for (Event e: events) {
               if (!e.getStatus().equals(EventStatus.DONE)){
                   e.setStatus(EventStatus.CANCELED);
                   e.setReasonToCancel("Doctor canceled");
                   eventServiceImp.updateEvent(e,appointmentDto.getId());
               }
            }
        }else if (appointmentDto.getStatus().equals(AppointmentStatus.DONE)){
            List<Event> events = eventServiceImp.findAllByAppointmentId(appointmentDto.getId());

            for (Event e: events) {
                if (!e.getStatus().equals(EventStatus.DONE)){
                    e.setStatus(EventStatus.DONE);
                    e.setReasonToCancel("Doctor closed the appointment");
                    eventServiceImp.updateEvent(e,appointmentDto.getId());
                }
            }
        }
    }


}
