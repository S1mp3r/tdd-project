package com.rafael.register.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private static final String URL = "/api/v1/users";
 
    @Autowired
    private Mock mockMvc;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    public void should_register_user_successfully() {
        var content = new UserRequest("Rafael", "1234", LocalDate.of(2002, 7, 25)

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(content)
                ))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("Rafael"));
    }

    @Test
    public void should_return_error_when_username_is_invalid() {
        var content = new UserRequest("Rafael", "1234", LocalDate.of(2002, 7, 25)

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(content)
                ))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("Rafael"));
    }
}
