package com.rafael.register.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafael.register.model.UserRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private static final String URL = "/api/v1/users";
 
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_register_user_successfully() throws JsonProcessingException, Exception {
        var content = new UserRequest("Rafael", "1234", LocalDate.of(2002, 7, 25));

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(content)
                ))
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @ParameterizedTest
    @CsvSource({
        "'', 1234, 2002-07-25",
        "Rafael, '', 2002-07-25",
        "Rafael, 1234, ''"
    })
    public void should_return_error_when_something_is_wrong(String name, String password, String date) throws JsonProcessingException, Exception {
        var content = new UserRequest(name, password, date.isBlank() ? null : LocalDate.parse(date));

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(content)
                ))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
