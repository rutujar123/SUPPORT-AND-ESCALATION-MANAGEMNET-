package com.company.supportsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.company.supportsystem.model")
public class SupportManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupportManagementSystemApplication.class, args);
	}

}
