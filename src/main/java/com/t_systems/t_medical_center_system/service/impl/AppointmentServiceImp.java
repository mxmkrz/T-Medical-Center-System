package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.exception.PatientNotFoundException;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentServiceImp {

    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;

    @Autowired
    public AppointmentServiceImp(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    public void addAppointment(Appointment appointment, Long id) {
        appointment.setPatient(patientRepository.findById(id).orElseThrow(PatientNotFoundException::new));
        appointmentRepository.save(appointment);
    }

    public List<Appointment> appointmentListByPatientId(Long id){
        return (List<Appointment>) appointmentRepository.findAllByPatientId(id);
    }


}
