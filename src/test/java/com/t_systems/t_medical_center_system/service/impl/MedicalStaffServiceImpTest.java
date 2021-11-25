package com.t_systems.t_medical_center_system.service.impl;

import com.t_systems.t_medical_center_system.entity.MedicalStaff;
import com.t_systems.t_medical_center_system.entity.enums.Role;
import com.t_systems.t_medical_center_system.repository.MedicalStaffRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class MedicalStaffServiceImpTest {
    @InjectMocks
    private MedicalStaffServiceImp medicalStaffServiceImp;
    @Mock
    private MedicalStaffRepository medicalStaffRepository;
    @Mock
    private BCryptPasswordEncoder cryptPasswordEncoder;

    @Test
    void saveStaff() {
        MedicalStaff medicalStaff = new MedicalStaff();
        medicalStaff.setPassword(cryptPasswordEncoder.encode("qwerty123"));
        medicalStaff.setRole(Role.ROLE_DOCTOR);

        medicalStaffServiceImp.saveStaff(medicalStaff);

        verify(medicalStaffRepository, times(1)).save(medicalStaff);
        assertFalse(CoreMatchers.is(medicalStaff.getRole()).matches(Role.ROLE_NURSE));
        assertNotEquals("qwerty123", medicalStaff.getPassword());
    }

}