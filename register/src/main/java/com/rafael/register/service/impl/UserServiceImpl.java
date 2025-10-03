package com.rafael.register.service.impl;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.rafael.register.entity.User;
import com.rafael.register.repository.UserRepository;
import com.rafael.register.service.UserService;
import com.rafael.register.utils.Constaints;

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

        if (age < Constaints.MIN_AGE) {
            throw new RuntimeException("User must be at least 18 years old to register.");
        }

        if (age > Constaints.MAX_AGE) {
            throw new RuntimeException("User must be at most 60 years old to register.");
        }

        if (user.getUsername().length() < Constaints.MIN_USERNAME_LENGTH) {
            throw new RuntimeException("Username must be at least 3 characters long.");
        }

        if (user.getUsername().length() > Constaints.MAX_USERNAME_LENGTH) {
            throw new RuntimeException("Username must be at most 10 characters long.");
        }

        if (user.getPassword().length() < Constaints.MIN_PASSWORD_LENGTH) {
            throw new RuntimeException("Password must be at least 4 characters long.");
        }

        if (user.getPassword().length() > Constaints.MAX_PASSWORD_LENGTH) {
            throw new RuntimeException("Password must be at most 6 characters long.");
        }
    }
}
