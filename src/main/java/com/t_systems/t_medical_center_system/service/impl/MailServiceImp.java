package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.exception.AppointmentNotFoundException;
import com.t_systems.t_medical_center_system.exception.PatientNotFoundException;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.repository.MedicalStaffRepository;
import com.t_systems.t_medical_center_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImp {

    public final JavaMailSender mailSender;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final MedicalStaffRepository medicalStaffRepository;
    private final SimpleMailMessage mailMessage;
    @Autowired
    public MailServiceImp(JavaMailSender mailSender, PatientRepository patientRepository, AppointmentRepository appointmentRepository, MedicalStaffRepository medicalStaffRepository, SimpleMailMessage simpleMailMessage) {
        this.mailSender = mailSender;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.medicalStaffRepository = medicalStaffRepository;
        this.mailMessage = simpleMailMessage;
    }






    public void sendSimpleMessage(AppointmentDto appointmentDto, Long idPatient) {
        Patient patient = patientRepository.findById(idPatient).orElseThrow(PatientNotFoundException::new);
        Appointment appointment = appointmentRepository.findById(appointmentDto.getId()).orElseThrow(AppointmentNotFoundException::new);
        MedicalStaff medicalStaff = medicalStaffRepository.findByName(patient.getDoctorName());
        mailMessage.setTo(medicalStaff.getEmail());
        mailMessage.setText("Name: " + appointment.getPatient().getName() + "\n" +
                "Surname: " + appointment.getPatient().getSurname() + "\n" +
                "Start Date:  " + appointment.getStartDate().toString() + "\n" +
                "End Date: " + appointment.getEndDate().toString() + "\n" +
                "Therapy Type: " + appointment.getTherapyType().name() + "\n" +
                "Week Day: " + appointment.getWeekDay().toString() + "\n" +
                "Time: " + appointment.getTimePatterns().toString() + "\n" +
                "Procedure: " + appointment.getProcedureList().toString() + "\n" +
                "Drugs: " + appointment.getDrugsList().toString());
        mailSender.send(mailMessage);
    }
}
