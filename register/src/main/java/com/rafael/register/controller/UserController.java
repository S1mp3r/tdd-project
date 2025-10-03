package com.rafael.register.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.register.entity.User;
import com.rafael.register.model.UserRequest;
import com.rafael.register.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService service;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void register(@Valid @RequestBody UserRequest user) {
        service.register(new User(null, user.getUsername(), user.getPassword(), user.getDateOfBirth()));
    }
}
