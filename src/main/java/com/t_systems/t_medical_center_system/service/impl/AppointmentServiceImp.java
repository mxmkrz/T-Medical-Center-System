package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.exception.PatientNotFoundException;
import com.t_systems.t_medical_center_system.repository.PatientRepository;
import com.t_systems.t_medical_center_system.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImp implements AppointmentService {

    private PatientRepository patientRepository;

    @Autowired
    public AppointmentServiceImp(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void addAppointment(Appointment appointment, Long id) {
        appointment.setPatient(patientRepository.findById(id).orElseThrow(PatientNotFoundException::new));



    }
}
