package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.entity.Event;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.entity.enums.AppointmentStatus;
import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.entity.enums.PatientStatus;
import com.t_systems.t_medical_center_system.exception.PatientNotFoundException;
import com.t_systems.t_medical_center_system.mapper.PatientMapper;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.repository.EventRepository;
import com.t_systems.t_medical_center_system.repository.MedicalStaffRepository;
import com.t_systems.t_medical_center_system.repository.PatientRepository;

import com.t_systems.t_medical_center_system.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class PatientServiceImp implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final EventRepository eventRepository;
    private final AppointmentRepository appointmentRepository;
    private final RabbitSender rabbitSender;
    private final MedicalStaffRepository medicalStaffRepository;

    @Autowired
    public PatientServiceImp(PatientRepository patientRepository, PatientMapper patientMapper, EventRepository eventRepository, AppointmentRepository appointmentRepository, RabbitSender rabbitSender, MedicalStaffRepository medicalStaffRepository) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.eventRepository = eventRepository;
        this.appointmentRepository = appointmentRepository;
        this.rabbitSender = rabbitSender;
        this.medicalStaffRepository = medicalStaffRepository;
    }


    @Transactional(readOnly = true)
    @Override
    public Page<PatientDto> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable).map(patientMapper::toDto);
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

        MedicalStaff medicalStaff = medicalStaffRepository.findByEmail(authStaff.getName());
        patient1.setDoctorName(medicalStaff.getName());
        patient1.setDoctorEmail(medicalStaff.getEmail());
        patientRepository.save(patient1);
        log.info("Add patient");
    }

    @Transactional
    @Override
    public void updatePatient(PatientDto patient) {
        Patient patient1 = patientMapper.toEntity(patient);
        Authentication authStaff = SecurityContextHolder.getContext().getAuthentication();
        MedicalStaff medicalStaff = medicalStaffRepository.findByEmail(authStaff.getName());
        patient1.setDoctorName(medicalStaff.getName());
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
        rabbitSender.sendMessage("delete patient");
    }

    /**
     * This is method necessary for discharge patient.
     * Function deletes all event after current date and update status appointment.
     *
     * @param patientDto the patientDto
     */
    @Transactional
    @Override
    public void discharge(PatientDto patientDto) {
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
