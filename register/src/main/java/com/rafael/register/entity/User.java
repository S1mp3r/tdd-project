package com.rafael.register.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class User {
    
    private String id;

    private String username;
    
    private int password;
    
    private LocalDate birthDate;

}
