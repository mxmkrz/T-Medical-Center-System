package com.t_systems.t_medical_center_system.util;

import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.repository.MedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;


@Component
public class StaffValidatorChange implements Validator {
    private final MedicalStaffRepository medicalStaffRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public StaffValidatorChange(MedicalStaffRepository medicalStaffRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.medicalStaffRepository = medicalStaffRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return MedicalStaff.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {


        MedicalStaff medicalStaff = (MedicalStaff) o;
        if (medicalStaff.getOldPassword().isEmpty()) errors.rejectValue("oldPassword", "", "Old password cannot be empty");
        if (medicalStaff.getNewPassword().isEmpty()) errors.rejectValue("newPassword", "", "New password cannot be empty");
        if (medicalStaff.getConfirmPassword().isEmpty()) errors.rejectValue("confirmPassword", "", "Confirm password cannot be empty");

        int countForOldPassword = 0;
        List<MedicalStaff> medicalStaffList = (List<MedicalStaff>) medicalStaffRepository.findAll();
        if (!medicalStaff.getOldPassword().isEmpty()) {
            for (MedicalStaff m : medicalStaffList) {
                if (bCryptPasswordEncoder.matches(medicalStaff.getOldPassword(), m.getPassword())) {
                    countForOldPassword++;
                }
            }
            if (countForOldPassword == 0) {
                errors.rejectValue("oldPassword", "", "No such password exists");
            }
        }
        int countForNewPassword = 0;
        if (!medicalStaff.getNewPassword().isEmpty()) {
            for (MedicalStaff m : medicalStaffList) {
                if (bCryptPasswordEncoder.matches(medicalStaff.getNewPassword(), m.getPassword())) {
                    countForNewPassword++;
                }
            }
            if (countForNewPassword > 0) {
                errors.rejectValue("newPassword", "", "Password already exists");
            }
        }
        if (!medicalStaff.getNewPassword().equals(medicalStaff.getConfirmPassword())) errors.rejectValue("confirmPassword", "", "New password and the Confirmed one do not match");
    }


}
