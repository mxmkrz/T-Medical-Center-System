package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.repository.AppointmentRepository;
import com.t_systems.t_medical_center_system.service.impl.AppointmentServiceImp;
import com.t_systems.t_medical_center_system.service.impl.EventServiceImp;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppointmentController {
    private AppointmentServiceImp appointmentServiceImp;
    private PatientServiceImp patientServiceImp;

    private EventServiceImp eventServiceImp;

    @Autowired
    public AppointmentController(AppointmentServiceImp appointmentServiceImp, PatientServiceImp patientServiceImp, EventServiceImp eventServiceImp) {
        this.appointmentServiceImp = appointmentServiceImp;
        this.patientServiceImp = patientServiceImp;
        this.eventServiceImp = eventServiceImp;
    }


    @GetMapping(value = "/doctor/profile/{id}/appointment")
    public String newAppointmentGet(@PathVariable(name = "id") Long id,
                                    Model model) {
        model.addAttribute("patient", patientServiceImp.getPatientById(id));
        model.addAttribute("appointmentNew", new AppointmentDto());
        return "templates/appointment";
    }


    @PostMapping(value = "/doctor/profile/{id}/appointment")
    public String newAppointmentPost(@PathVariable(name = "id") Long id,
                                     @ModelAttribute(value = "appointmentNew") AppointmentDto appointmentDto) {

        appointmentServiceImp.addAppointment(appointmentDto, id);


        return "redirect:/doctor/profile/{id}";
    }


    @GetMapping(value = "/doctor/profile/{id}/pageAppointment")
    public String getAppointmentPage(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("pageAppointment", appointmentServiceImp.getAppointmentListByPatient(id));
        model.addAttribute("patient", patientServiceImp.getPatientById(id));
        return "templates/pageAppointment";
    }


    //*******************************************
    @GetMapping(value = "/doctor/profile/{id}/edit/{idAppointment}")
    public String editAppointmentGet(@PathVariable(name = "id") Long id,
                                     @PathVariable(name = "idAppointment") Long idAppointment, Model model) {
        model.addAttribute("patient", patientServiceImp.getPatientById(id));
        model.addAttribute("editAppointment", appointmentServiceImp.gitAppointById(idAppointment));
        return "templates/appointmentEdit";
    }


    @PostMapping(value = "/doctor/profile/{id}/edit/{idAppointment}")
    public String editAppointmentPost(@PathVariable(name = "id") Long id,
                                      @PathVariable(name = "idAppointment") Long idAppointment, @ModelAttribute(value = "appointmentEdit") AppointmentDto appointmentDto) {
        appointmentServiceImp.updateAppointment(appointmentDto, id);
        return "redirect:/doctor/profile/{id}";
    }


}
