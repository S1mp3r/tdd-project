package com.rafael.register.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.rafael.register.entity.User;
import com.rafael.register.repository.UserRepository;
import com.rafael.register.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User register(User user) {
        return repository.save(user);
    }
    
}
