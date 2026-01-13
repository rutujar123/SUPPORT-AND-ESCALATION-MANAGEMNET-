package com.company.supportsystem.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.supportsystem.model.Ticket;
import com.company.supportsystem.services.SupportTicketService;

@RestController
@RequestMapping("/support/tickets")
public class SupportController {

    private final SupportTicketService supportTicketServices;

    public SupportController(SupportTicketService supportTicketServices) {
        this.supportTicketServices = supportTicketServices;
    }

    @PostMapping("/{ticketId}/resolve")
    public Ticket resolveTicket(
            @PathVariable Long ticketId,
            @RequestParam("supportUsername") String supportUsername
    ) {
        return supportTicketServices.resolveTicket(ticketId, supportUsername);
    }
}
