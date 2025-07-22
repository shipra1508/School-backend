package com.school.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.dto.LoginDTO;
import com.school.dto.RegisterDTO;
import com.school.role.Role;
import com.school.service.AuthService;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerUserSuccessfully() throws Exception {
        RegisterDTO request = new RegisterDTO("teacher1", "pass123", Role.TEACHER);
        Mockito.when(authService.register(Mockito.any(RegisterDTO.class)))
               .thenReturn(request);

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(content().string("teacher1 of role TEACHER registered successfully!!"));
    }

    @Test
    void loginUserSuccessfully() throws Exception {
        LoginDTO request = new LoginDTO("teacher1", "pass123",Role.TEACHER);
        Mockito.when(authService.login(Mockito.any(LoginDTO.class)))
               .thenReturn(request);

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isAccepted())
            .andExpect(content().string("teacher1 logged in successfully as TEACHER"));

    }
}
