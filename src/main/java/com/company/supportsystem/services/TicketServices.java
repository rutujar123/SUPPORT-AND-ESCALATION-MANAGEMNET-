package com.company.supportsystem.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.company.supportsystem.model.Role;
import com.company.supportsystem.model.Ticket;
import com.company.supportsystem.model.TicketStatus;
import com.company.supportsystem.model.User;
import com.company.supportsystem.repository.TicketRepo;
import com.company.supportsystem.repository.UserRepo;

@Service
public class TicketServices {

    private final TicketRepo ticketRepo;
    private final UserRepo userRepo;

    public TicketServices(TicketRepo ticketRepo, UserRepo userRepo) {
        this.ticketRepo = ticketRepo;
        this.userRepo = userRepo;
    }

    // ✅ MERCHANT – RAISE TICKET
    public Ticket raiseTicket(String username, Ticket ticket, MultipartFile file) {

        User merchant = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Merchant not found"));

        if (merchant.getRole() != Role.MERCHANT) {
            throw new RuntimeException("Only MERCHANT can raise ticket");
        }

        if (file != null && !file.isEmpty()) {
            ticket.setAttachmentPath("uploads/" + file.getOriginalFilename());
        }

        ticket.setMerchant(merchant);
        ticket.setStatus(TicketStatus.NEW);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());
        ticket.setAssignedSupport(null);

        return ticketRepo.save(ticket);
    }

    // ✅ MERCHANT – VIEW OWN TICKETS
    public List<Ticket> getAllTicketsForMerchant(String username) {

        User merchant = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Merchant not found"));

        if (merchant.getRole() != Role.MERCHANT) {
            throw new RuntimeException("Only MERCHANT can view tickets");
        }

        return ticketRepo.findByMerchant(merchant);
    }
}
