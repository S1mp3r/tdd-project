package com.rafael.register.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 10, message = "Username must be between 3 and 10 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 4, max = 6, message = "Password must be between 3 and 10 characters")
    private String password;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

}
