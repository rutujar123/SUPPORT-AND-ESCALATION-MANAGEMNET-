package com.company.supportsystem.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.supportsystem.dto.BankEscalationRequest;
import com.company.supportsystem.services.BankServices;

@RestController
@RequestMapping("/bank")
public class BankController {

    private final BankServices bankService;

    public BankController(BankServices bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/submit")
    public String submitToBank(@RequestBody BankEscalationRequest request) {
        bankService.submitToBank(request);
        return "Ticket sent to bank successfully";
    }
}
