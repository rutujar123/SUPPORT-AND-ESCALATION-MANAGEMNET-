package com.company.supportsystem.services;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.company.supportsystem.dto.RegistrationRequest;
import com.company.supportsystem.model.RegistrationUser;
import com.company.supportsystem.model.Role;
import com.company.supportsystem.model.User;
import com.company.supportsystem.repository.RegistrationUserRepo;
import com.company.supportsystem.repository.UserRepo;

@Service
public class RegistrationService {

    private final RegistrationUserRepo registrationRepo;
    private final UserRepo userRepo;

    public RegistrationService(RegistrationUserRepo registrationRepo, UserRepo userRepo) {
        this.registrationRepo = registrationRepo;
        this.userRepo = userRepo;
    }

    public RegistrationUser register(RegistrationRequest request) {

        // 1️⃣ Save in registration_users
        RegistrationUser regUser = new RegistrationUser();
        regUser.setUsername(request.getUsername());
        regUser.setPassword(request.getPassword());
        regUser.setEmail(request.getEmail());
        regUser.setMobileNumber(request.getMobileNumber());
        regUser.setName(request.getName());
        regUser.setMid(request.getMid());
        regUser.setCreatedAt(LocalDateTime.now());

        registrationRepo.save(regUser);

        // 2️⃣ Save in users (FOR LOGIN)
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(Role.MERCHANT);
        user.setStatus("ACTIVE");
        user.setCreatedAt(LocalDateTime.now());

        userRepo.save(user);

        return regUser;
    }
}
