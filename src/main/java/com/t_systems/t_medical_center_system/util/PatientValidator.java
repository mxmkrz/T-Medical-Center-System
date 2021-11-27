package com.t_systems.t_medical_center_system.util;

import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.entity.Patient;
import com.t_systems.t_medical_center_system.mapper.PatientMapper;
import com.t_systems.t_medical_center_system.repository.PatientRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class PatientValidator implements Validator {
    private final PatientRepository patientRepository;

    public PatientValidator(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PatientDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PatientDto patientDto = (PatientDto) o;
        List<Patient> patients = (List<Patient>) patientRepository.findAll();
        for (Patient p:patients) {
            if (p.getInsuranceNumber().equals(patientDto.getInsuranceNumber())){
                errors.rejectValue("insuranceNumber","","Patient with such an insurance number already exists");
            }
        }

    }
}
