package com.t_systems.t_medical_center_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Appointment was not found")
public class AppointmentNotFoundException extends RuntimeException {
}
