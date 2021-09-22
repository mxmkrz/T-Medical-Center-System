package com.t_systems.T_Medical_Center_System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Entity was not found")
public class PatientNotFoundException extends RuntimeException {
}
