package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.entity.Event;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.entity.enums.AppointmentStatus;
import com.t_systems.t_medical_center_system.mapper.Convertor;
import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.entity.enums.PatientStatus;
import com.t_systems.t_medical_center_system.exception.PatientNotFoundException;
import com.t_systems.t_medical_center_system.mapper.PatientMapper;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.repository.EventRepository;
import com.t_systems.t_medical_center_system.repository.PatientRepository;

import com.t_systems.t_medical_center_system.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class PatientServiceImp implements PatientService {

    private final PatientRepository patientRepository;
    private final Convertor<Patient, PatientDto> patientConvertor;
    private final PatientMapper patientMapper;
    private final EventRepository eventRepository;
    private final AppointmentRepository appointmentRepository;


    @Autowired
    public PatientServiceImp(PatientRepository patientRepository, Convertor<Patient, PatientDto> patientConvertor, PatientMapper patientMapper, EventRepository eventRepository, AppointmentRepository appointmentRepository, EventRepository eventRepository1) {
        this.patientRepository = patientRepository;
        this.patientConvertor = patientConvertor;
        this.patientMapper = patientMapper;
        this.appointmentRepository = appointmentRepository;
        this.eventRepository = eventRepository1;
    }


    @Transactional(readOnly = true)
    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> result = (List<Patient>) patientRepository.findAll();
        return patientConvertor.convertLisToDto(result, PatientDto.class);
    }


    @Transactional(readOnly = true)
    @Override
    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(PatientNotFoundException::new);
        return patientMapper.toDto(patient);
    }

    @Transactional
    @Override
    public void savePatient(PatientDto patient) {
        Patient patient1 = patientMapper.toEntity(patient);
        Authentication authStaff = SecurityContextHolder.getContext().getAuthentication();
        patient1.setDoctorName(authStaff.getName());
        patientRepository.save(patient1);
        log.info("Add patient");
    }

    @Transactional
    @Override
    public void updatePatient(PatientDto patient) {
        Patient patient1 = patientMapper.toEntity(patient);
        Authentication authStaff = SecurityContextHolder.getContext().getAuthentication();
        patient1.setDoctorName(authStaff.getName());
        patientRepository.save(patient1);
        log.info("Update patient");

    }


    @Transactional
    @Override
    public void deletePatient(Long id) {
        List<Event> events = eventRepository.findAllByPatientId(id);
        eventRepository.deleteAll(events);
        List<Appointment> appointments = appointmentRepository.findAllByPatientId(id);
        appointmentRepository.deleteAll(appointments);
        patientRepository.deleteById(id);
    }


    @Transactional
    public void patientDischarge(PatientDto patientDto) {
        if (patientDto.getStatus().equals(PatientStatus.DISCHARGED.name())) {
            List<Event> events = eventRepository.findAllByPatientId(patientDto.getId());
            Date currentDate = new Date();

            for (Event e : events) {
                if (e.getDate().after(currentDate)) {
                    eventRepository.delete(e);

                }
            }

            Patient patient = patientMapper.toEntity(patientDto);
            List<Appointment> appointments = appointmentRepository.findAllByPatientId(patientDto.getId());
            for (Appointment a : appointments) {
                a.setStatus(AppointmentStatus.FINISHED);
                a.setPatient(patient);
                appointmentRepository.save(a);
            }
        }
        log.info("All events from this moment have been deleted from the discharged patient");
        log.info("The patient has been discharged and all appointments have the status finished");

    }


}
