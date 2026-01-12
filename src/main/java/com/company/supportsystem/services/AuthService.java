package com.company.supportsystem.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.company.supportsystem.model.User;
import com.company.supportsystem.repository.UserRepo;

@Service
public class AuthService {
    private final UserRepo userRepo;
      public AuthService(UserRepo userRepo)
      {
    	  this.userRepo=userRepo;
      }
      public User login(String username,String password)
      {
    	  User user = userRepo.findByUsername(username)
                  .orElseThrow(() -> new RuntimeException("Invalid username or password"));
    	  if (!user.getPassword().equals(password)) {
              throw new RuntimeException("Invalid username or password");
          }

          if (!"ACTIVE".equals(user.getStatus())) {
              throw new RuntimeException("User is inactive");
          }

          // update last login time
          user.setLastLoginAt(LocalDateTime.now());
          userRepo.save(user);
		return user;
    	  
      }
}
