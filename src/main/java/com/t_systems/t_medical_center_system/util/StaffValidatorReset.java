package com.t_systems.t_medical_center_system.util;

import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StaffValidatorChange implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return MedicalStaff.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MedicalStaff medicalStaff = (MedicalStaff) o;
        if (medicalStaff.getEmail() == null){
            errors.rejectValue("email", "", "Email can't be empty");
        }
        Pattern p = Pattern.compile("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b");
        Matcher m = p.matcher(medicalStaff.getEmail());
        if (!m.find()){
            errors.rejectValue("email", "", "Incorrect email format");
        }


    }
}
