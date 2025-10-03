package com.rafael.register.entity;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {
    
    @MongoId
    private String id;

    private String username;
    
    private int password;
    
    private LocalDate birthDate;

}
