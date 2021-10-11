package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.mapper.Convertor;
import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImp {

    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;
    private Convertor<Appointment,AppointmentDto> convertor;

    @Autowired
    public AppointmentServiceImp(AppointmentRepository appointmentRepository, PatientRepository patientRepository, Convertor<Appointment, AppointmentDto> convertor) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.convertor = convertor;
    }



    public void addAppointment(AppointmentDto appointment,Long id) {



    }

//    public List<Appointment> appointmentListByPatientId(Long id){
//        return (List<Appointment>) appointmentRepository.findAllByPatientId(id);
//    }


}
