package com.company.supportsystem.controller;

import org.springframework.web.bind.annotation.*;

import com.company.supportsystem.model.User;
import com.company.supportsystem.services.AuthService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public User login(@RequestBody User request) {
        return authService.login(
                request.getUsername(),
                request.getPassword()
        );
    }
}
