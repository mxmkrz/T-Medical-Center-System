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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class MedicalStaffServiceImpTest {
    @InjectMocks
    private MedicalStaffServiceImp medicalStaffServiceImp;
    @Mock
    private MedicalStaffRepository medicalStaffRepository;
    @Mock
    private BCryptPasswordEncoder cryptPasswordEncoder;
    @Mock
    private JavaMailSender mailSender;
    @Mock
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

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

//
//    @Test
//    void changePassword(){
//        MedicalStaff medicalStaff = new MedicalStaff();
//        List<MedicalStaff> medicalStaffList = List.of(medicalStaff);
//        medicalStaff.setOldPassword("$2y$10$FWPOg/32YrZHEPOk/PqucOaMqqlhqa.So4MxNngqL0zV1nPk7GF9y");
//        medicalStaff.setPassword("$2y$10$FWPOg/32YrZHEPOk/PqucOaMqqlhqa.So4MxNngqL0zV1nPk7GF9y");
//        medicalStaff.setNewPassword("123");
//        when(medicalStaffRepository.findAll()).thenReturn(medicalStaffList);
//
//        medicalStaffServiceImp.changePassword(medicalStaff);
//
//       assertEquals(bCryptPasswordEncoder.encode("123"),medicalStaff.getPassword());
//    }
}