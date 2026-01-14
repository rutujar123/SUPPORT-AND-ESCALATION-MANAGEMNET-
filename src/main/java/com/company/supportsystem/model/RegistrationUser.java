package com.company.supportsystem.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "registration_users")
public class RegistrationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String mobileNumber;
    private String name;
    private String mid;
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
    
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getMid() {
        return mid;
    }
    
    public void setMid(String mid) {
        this.mid = mid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
