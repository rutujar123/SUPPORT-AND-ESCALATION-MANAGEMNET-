package com.company.supportsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.supportsystem.dto.BankEmailDraftDTO;
import com.company.supportsystem.model.BankEscalation;
import com.company.supportsystem.model.Ticket;
import com.company.supportsystem.services.BankDraftService;
import com.company.supportsystem.services.BankServices;
import com.company.supportsystem.services.TicketServices;

@RestController
@RequestMapping("/support/bank")
public class BankController {


    private final TicketServices ticketServices;
    private final BankServices bankServices;
    private final BankDraftService draftService; 
    public BankController(
       
            TicketServices ticketServices,
            BankServices bankServices,
            BankDraftService draftService) {

      
        this.ticketServices = ticketServices;
        this.bankServices = bankServices;
        this.draftService = draftService;
    }

    // SUPPORT clicks "Submit to Bank"
    @PostMapping("/send/{ticketId}")
    public String sendToBank(@PathVariable Long ticketId) {

        // ✅ Correct method name
        Ticket ticket = ticketServices.getTicketById(ticketId);

        
       
        return "send";
    }
    @GetMapping("/draft/{ticketId}")
    public BankEmailDraftDTO getDraft(@PathVariable Long ticketId) {

        Ticket ticket = ticketServices.getTicketById(ticketId);
        BankEscalation escalation = bankServices.getByTicketId(ticketId);

        return draftService.generateDraft(ticket, escalation);
    }
}
