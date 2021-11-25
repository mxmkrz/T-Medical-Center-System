package com.t_systems.t_medical_center_system.controller;

import com.t_systems.t_medical_center_system.dto.AppointmentDto;
import com.t_systems.t_medical_center_system.dto.PatientDto;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class AppointmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "DOCTOR")
    void getAppointments() throws Exception {
        ResultActions result = mockMvc.perform(get("/doctor/profile/{id}/appointments",2L));
        assertEquals(result.andReturn().getResponse().getStatus(), HttpStatus.OK.value());
    }

    @Test
    @WithMockUser(roles = "NURSE")
    void cancelOrDoneAppointment() throws Exception {
        AppointmentDto appointmentDto = new AppointmentDto();
        ResultActions result = mockMvc.perform(post("/doctor/profile/{id}/pageAppointment",2L)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept("*/*")
                .content(appointmentDto.toString()));
        Assertions.assertEquals(result.andReturn().getResponse().getStatus(),HttpStatus.FORBIDDEN.value());
    }
}