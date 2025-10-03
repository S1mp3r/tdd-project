package com.rafael.register.service.impl;

import java.time.LocalDate;
import java.time.Period;

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
        validateUser(user);
        return repository.save(user);
    }
    
    private void validateUser(User user) {
        var age = Period.between(user.getBirthDate(), LocalDate.now()).getYears();

        if (age < 18) {
            throw new RuntimeException("User must be at least 18 years old to register.");
        }

        if (age > 60) {
            throw new RuntimeException("User must be at most 60 years old to register.");
        }
    }
}
