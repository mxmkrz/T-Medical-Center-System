package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.dto.PatientDto;
import com.t_systems.t_medical_center_system.repository.PatientRepository;
import com.t_systems.t_medical_center_system.service.impl.PatientServiceImp;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(roles = "DOCTOR")
    void addPatientPost() throws Exception {
        PatientDto patientDto = new PatientDto();
        ResultActions result = mockMvc.perform(post("/doctor/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(patientDto.toString()));
        Assertions.assertEquals(result.andReturn().getResponse().getStatus(),HttpStatus.OK.value());
    }


    @Test
    @WithMockUser(roles = "NURSE")
    void getPatientProfile() throws Exception {
        ResultActions result = mockMvc.perform(get("/doctor/profile/{id}",1L));
        Assertions.assertEquals(result.andReturn().getResponse().getStatus(),HttpStatus.FORBIDDEN.value());
    }


}