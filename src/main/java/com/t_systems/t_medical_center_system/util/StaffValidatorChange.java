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
public class StaffValidatorReset implements Validator {
    private final MedicalStaffRepository medicalStaffRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public StaffValidatorReset(MedicalStaffRepository medicalStaffRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
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
        if (medicalStaff.getOldPassword() == null){
            errors.rejectValue("oldPassword", "", "Old password cannot be empty");
        }
        if (medicalStaff.getNewPassword() == null){
            errors.rejectValue("newPassword", "", "New password cannot be empty");
        }
        if (medicalStaff.getConfirmPassword() == null){
            errors.rejectValue("confirmPassword", "", "Confirm password cannot be empty");
        }
        if (!medicalStaff.getNewPassword().equals(medicalStaff.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "", "Passwords don't match");
        }
        int countForOldPassword = 0;
        List<MedicalStaff> medicalStaffList = (List<MedicalStaff>) medicalStaffRepository.findAll();
        for (MedicalStaff m : medicalStaffList) {
            if (bCryptPasswordEncoder.matches(medicalStaff.getOldPassword(), m.getPassword())) {
                countForOldPassword++;
            }
        }
        if (countForOldPassword > 0){
            errors.rejectValue("oldPassword", "", "Old password don't match");
        }
        int countForNewPassword = 0;
        for (MedicalStaff m : medicalStaffList) {
            if (bCryptPasswordEncoder.matches(medicalStaff.getNewPassword(), m.getPassword())) {
                countForNewPassword++;
            }
        }
        if (countForNewPassword > 0){
            errors.rejectValue("newPassword", "", "Password already exists");
        }



    }
}
