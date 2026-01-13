package com.company.supportsystem.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.supportsystem.model.User;
import com.company.supportsystem.services.AuthService;

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
