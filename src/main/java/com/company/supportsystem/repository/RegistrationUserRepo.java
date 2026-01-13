package com.company.supportsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.supportsystem.model.RegistrationUser;

@Repository
public interface RegistrationUserRepo extends JpaRepository<RegistrationUser, Long> {
}
