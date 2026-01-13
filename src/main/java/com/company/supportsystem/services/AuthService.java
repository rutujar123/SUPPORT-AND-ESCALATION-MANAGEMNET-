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

        System.out.println("LOGIN API HIT");

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        System.out.println("DB USERNAME = " + user.getUsername());
        System.out.println("DB PASSWORD = " + user.getPassword());
        System.out.println("DB ROLE     = " + user.getRole());
        System.out.println("DB STATUS   = " + user.getStatus());

        // password check
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password");
        }

        // status check
        if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
            throw new RuntimeException("User is inactive");
        }

        // 🔴 IMPORTANT: SUPPORT role only
        if (user.getRole() != Role.SUPPORT) {
            throw new RuntimeException("Only SUPPORT users are allowed to login");
        }

        // update last login
        user.setLastLoginAt(LocalDateTime.now());
        userRepo.save(user);

        return user;
    }
}
