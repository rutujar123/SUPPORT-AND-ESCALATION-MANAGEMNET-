package com.company.supportsystem.controller;

import org.springframework.web.bind.annotation.*;

import com.company.supportsystem.dto.RegistrationRequest;
import com.company.supportsystem.model.RegistrationUser;
import com.company.supportsystem.services.RegistrationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/create")
    public RegistrationUser register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}
