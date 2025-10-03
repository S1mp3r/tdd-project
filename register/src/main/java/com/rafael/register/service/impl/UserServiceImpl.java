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

        if (age < Constaints.MIN_AGE || age > Constaints.MAX_AGE) {
            throw new RuntimeException("Invalid age.");
        }

        if (user.getUsername().length() < Constaints.MIN_USERNAME_LENGTH || user.getUsername().length() > Constaints.MAX_USERNAME_LENGTH) {
            throw new RuntimeException("Invalid username.");
        }

        if (user.getPassword().length() < Constaints.MIN_PASSWORD_LENGTH || user.getPassword().length() > Constaints.MAX_PASSWORD_LENGTH) {
            throw new RuntimeException("Invalid password");
        }

    }
}
