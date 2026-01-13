package com.company.supportsystem.services;

import org.springframework.stereotype.Service;

import com.company.supportsystem.dto.RegistrationRequest;
import com.company.supportsystem.model.RegistrationUser;
import com.company.supportsystem.repository.RegistrationUserRepo;

@Service
public class RegistrationService {

    private final RegistrationUserRepo registrationUserRepo;

    public RegistrationService(RegistrationUserRepo registrationUserRepo) {
        this.registrationUserRepo = registrationUserRepo;
    }

    public RegistrationUser register(RegistrationRequest request) {

        RegistrationUser user = new RegistrationUser();
        user.setMid(request.getMid());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setMobileNumber(request.getMobileNumber());

        return registrationUserRepo.save(user);
    }
}
