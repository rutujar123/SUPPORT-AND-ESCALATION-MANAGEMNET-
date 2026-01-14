package com.company.supportsystem.services;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.company.supportsystem.model.Role;
import com.company.supportsystem.model.User;
import com.company.supportsystem.repository.UserRepo;

@Service
public class AuthService {

    private final UserRepo userRepo;

    public AuthService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User login(String username, String password) {

        User user = userRepo.findByUsername(username.trim())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!user.getPassword().equals(password.trim())) {
            throw new RuntimeException("Invalid username or password");
        }

        if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
            throw new RuntimeException("User is inactive");
        }

        if (user.getRole() != Role.SUPPORT && user.getRole() != Role.MERCHANT) {
            throw new RuntimeException("User role not allowed");
        }

        user.setLastLoginAt(LocalDateTime.now());
        userRepo.save(user);

        return user;
    }
}
