package com.company.supportsystem.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.company.supportsystem.model.Role;
import com.company.supportsystem.model.Ticket;
import com.company.supportsystem.model.User;
import com.company.supportsystem.repository.UserRepo;
import com.company.supportsystem.services.TicketServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/merchant/tickets")
public class TicketController {

    private final TicketServices service;
    private final ObjectMapper objectMapper;

    public TicketController(TicketServices service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @PostMapping(value = "/raise", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Ticket raiseTicket(
            @RequestParam("username") String username,
            @RequestPart("ticket") String ticketJson,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws Exception {

        System.out.println("API HIT");
        System.out.println("USERNAME = " + username);
        System.out.println("TICKET JSON = " + ticketJson);
        Ticket ticket = objectMapper.readValue(ticketJson, Ticket.class);

        return service.raiseTicket(username, ticket, file);
    }
    @GetMapping("/my")
    public List<Ticket> getAllMyTickets(@RequestParam String username) {
        return service.getAllTicketsForMerchant(username);
    }


}
