package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.dto.AppointmentListWrapper;
import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Appointment;
import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.service.impl.AppointmentServiceImp;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppointmentController {
    private AppointmentServiceImp appointmentServiceImp;
    private PatientServiceImp patientServiceImp;
    private AppointmentRepository appointmentRepository;




    @Autowired
    public AppointmentController(AppointmentServiceImp appointmentServiceImp, PatientServiceImp patientServiceImp, AppointmentRepository appointmentRepository) {
        this.appointmentServiceImp = appointmentServiceImp;
        this.patientServiceImp = patientServiceImp;
        this.appointmentRepository = appointmentRepository;
    }


    @GetMapping(value = "/patient/profile/{id}/appointment")
    public String newAppointmentGet(Model model,@PathVariable(name = "id")Long id) {
        PatientDto patient = patientServiceImp.getPatientById(id);

        AppointmentListWrapper wrapper = new AppointmentListWrapper();
        wrapper.setAppointmentDtoArrayList(new ArrayList<>(appointmentServiceImp.getAllAppointments()));


        model.addAttribute("patientId",patient);
        model.addAttribute("appointmentListWrapper", wrapper);
        return "templates/appointment";


    }
    @PostMapping(value = "/patient/profile/{id}/appointment")
    public String newAppointmentPost(@PathVariable(name = "id")Long id, @ModelAttribute(value = "appointmentListWrapper") AppointmentListWrapper appointmentListWrapper) {

        appointmentServiceImp.addAppointment(appointmentListWrapper.getAppointmentDtoArrayList(),id);
        return "redirect:/patient/patients";

    }



}
