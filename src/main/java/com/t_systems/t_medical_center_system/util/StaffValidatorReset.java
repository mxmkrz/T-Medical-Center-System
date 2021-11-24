package com.t_systems.t_medical_center_system.util;

import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.repository.MedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class StaffValidatorReset implements Validator {
    private MedicalStaffRepository medicalStaffRepository;
    @Autowired
    public StaffValidatorReset(MedicalStaffRepository medicalStaffRepository) {
        this.medicalStaffRepository = medicalStaffRepository;
    }




    @Override
    public boolean supports(Class<?> aClass) {
        return MedicalStaff.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MedicalStaff medicalStaff = (MedicalStaff) o;
        boolean checkEmail = false;
        List<MedicalStaff> medicalStaffList = (List<MedicalStaff>) medicalStaffRepository.findAll();
        for (MedicalStaff m: medicalStaffList) {
            if (m.getEmail().equals(medicalStaff.getEmail())) {
                checkEmail = true;
                break;
            }
        }
        if (!checkEmail){
            errors.rejectValue("email","","Staff with this email not exist");
        }



    }
}
