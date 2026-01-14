package com.company.supportsystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.supportsystem.model.Ticket;
import com.company.supportsystem.services.SupportTicketService;

@RestController
@RequestMapping("/support/tickets")
@CrossOrigin(origins = "*")
public class SupportController {

    private final SupportTicketService supportTicketServices;

    public SupportController(SupportTicketService supportTicketServices) {
        this.supportTicketServices = supportTicketServices;
    }

    // ✅ SUPPORT DASHBOARD – LIST NEW TICKETS
    @GetMapping("/new")
    public List<Ticket> getNewTickets(
            @RequestParam("supportUsername") String supportUsername) {

        return supportTicketServices.getAllNewTickets(supportUsername);
    }

    // ✅ RESOLVE TICKET
    @PostMapping("/{ticketId}/resolve")
    public Ticket resolveTicket(
            @PathVariable Long ticketId,
            @RequestParam("supportUsername") String supportUsername) {

        return supportTicketServices.resolveTicket(ticketId, supportUsername);
    }
    @PostMapping("/{ticketId}/assign")
    public Ticket assignTicket(
            @PathVariable Long ticketId,
            @RequestParam("supportUsername") String supportUsername) {

        return supportTicketServices.assignTicketToSupport(ticketId, supportUsername);
    }

}
