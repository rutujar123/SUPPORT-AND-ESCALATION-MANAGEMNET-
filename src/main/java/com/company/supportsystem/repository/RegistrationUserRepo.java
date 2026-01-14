package com.company.supportsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.supportsystem.model.RegistrationUser;

public interface RegistrationUserRepo
        extends JpaRepository<RegistrationUser, Long> {
}
