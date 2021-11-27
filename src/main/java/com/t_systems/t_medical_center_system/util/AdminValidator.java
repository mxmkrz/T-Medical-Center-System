package com.t_systems.t_medical_center_system.util;

import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.repository.MedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AdminValidator implements Validator {
    private MedicalStaffRepository medicalStaffRepository;

    @Autowired
    public AdminValidator(MedicalStaffRepository medicalStaffRepository) {
        this.medicalStaffRepository = medicalStaffRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return MedicalStaff.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MedicalStaff medicalStaff = (MedicalStaff) o;
        if (medicalStaff.getEmail().isEmpty()) {
            errors.rejectValue("email", "", "Email should be not empty");
        }
        if (medicalStaff.getName().isEmpty()) {
            errors.rejectValue("name", "", "Name should be not empty");
        }
        if (medicalStaff.getSurname().isEmpty()) {
            errors.rejectValue("surname", "", "Surname should be not empty");
        }
        if (medicalStaff.getPassword().isEmpty()) {
            errors.rejectValue("password", "", "Password should be not empty");
        }
        if (medicalStaff.getConfirmPasswordStaff().isEmpty()) {
            errors.rejectValue("confirmPasswordStaff", "", "Confirm Password should be not empty");
        }
        if (!medicalStaff.getPassword().equals(medicalStaff.getConfirmPasswordStaff())) {
            errors.rejectValue("confirmPasswordStaff", "", "New password and the Confirmed one do not match");
        }



    }
}
