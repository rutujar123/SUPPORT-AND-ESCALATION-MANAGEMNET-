//package com.company.supportsystem.services;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import com.company.supportsystem.model.Role;
//import com.company.supportsystem.model.Ticket;
//import com.company.supportsystem.model.TicketStatus;
//import com.company.supportsystem.model.User;
//import com.company.supportsystem.repository.TicketRepo;
//import com.company.supportsystem.repository.UserRepo;
//
//@Service
//public class SupportTicketService {
//
//    private final TicketRepo ticketRepo;
//    private final UserRepo userRepo;
//
//    public SupportTicketService(TicketRepo ticketRepo, UserRepo userRepo) {
//        this.ticketRepo = ticketRepo;
//        this.userRepo = userRepo;
//    }
//
//    // ✅ 1️⃣ SUPPORT DASHBOARD – ALL NEW TICKETS
//    public List<Ticket> getAllNewTickets(String supportUsername) {
//
//        User support = userRepo.findByUsername(supportUsername)
//                .orElseThrow(() -> new RuntimeException("Support not found"));
//
//        if (support.getRole() != Role.SUPPORT) {
//            throw new RuntimeException("Only SUPPORT can view tickets");
//        }
//
//        return ticketRepo.findByStatus(TicketStatus.NEW);
//    }
//
//    // ✅ 2️⃣ RESOLVE TICKET
//    public Ticket resolveTicket(Long ticketId, String supportUsername) {
//
//        User support = userRepo.findByUsername(supportUsername)
//                .orElseThrow(() -> new RuntimeException("Support not found"));
//
//        if (support.getRole() != Role.SUPPORT) {
//            throw new RuntimeException("Only SUPPORT can resolve tickets");
//        }
//
//        Ticket ticket = ticketRepo.findById(ticketId)
//                .orElseThrow(() -> new RuntimeException("Ticket not found"));
//
//        // ❗ validations
//        if (ticket.getStatus() == TicketStatus.RESOLVED) {
//            throw new RuntimeException("Ticket already resolved");
//        }
//
//        if (ticket.getAssignedSupport() != null &&
//            !ticket.getAssignedSupport().getUserId().equals(support.getUserId())) {
//            throw new RuntimeException("Ticket assigned to another support");
//        }
//
//        ticket.setAssignedSupport(support);
//        ticket.setStatus(TicketStatus.RESOLVED);
//        ticket.setUpdatedAt(LocalDateTime.now());
//
//        return ticketRepo.save(ticket);
//    }
//    public Ticket assignTicketToSupport(Long ticketId, String supportUsername) {
//
//        User support = userRepo.findByUsername(supportUsername)
//                .orElseThrow(() -> new RuntimeException("Support not found"));
//
//        if (support.getRole() != Role.SUPPORT) {
//            throw new RuntimeException("Only SUPPORT can assign tickets");
//        }
//
//        Ticket ticket = ticketRepo.findById(ticketId)
//                .orElseThrow(() -> new RuntimeException("Ticket not found"));
//
//        if (ticket.getAssignedSupport() != null) {
//            throw new RuntimeException("Ticket already assigned");
//        }
//
//        ticket.setAssignedSupport(support);
//        ticket.setStatus(TicketStatus.UNDER_REVIEW);
//        ticket.setUpdatedAt(LocalDateTime.now());
//        System.out.println("ASSIGN API HIT for ticketId = " + ticketId);
//
//        return ticketRepo.save(ticket);
//    }
//
//}

package com.company.supportsystem.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.company.supportsystem.model.*;
import com.company.supportsystem.repository.*;

@Service
public class SupportTicketService {

    private final TicketRepo ticketRepo;
    private final UserRepo userRepo;

    public SupportTicketService(TicketRepo ticketRepo, UserRepo userRepo) {
        this.ticketRepo = ticketRepo;
        this.userRepo = userRepo;
    }

    // ===============================
    // 1️⃣ SUPPORT DASHBOARD – NEW TICKETS
    // ===============================
    public List<Ticket> getAllTicketsForSupport(String supportUsername) {

        User support = userRepo.findByUsername(supportUsername)
                .orElseThrow(() -> new RuntimeException("Support not found"));

        if (support.getRole() != Role.SUPPORT) {
            throw new RuntimeException("Only SUPPORT can view tickets");
        }

        // ✅ FETCH ALL TICKETS (no status filter)
        return ticketRepo.findAll();
    }


    // ===============================
    // 2️⃣ ASSIGN TICKET TO SUPPORT
    // ===============================
    public Ticket assignTicketToSupport(Long ticketId, String supportUsername) {

        User support = userRepo.findByUsername(supportUsername)
                .orElseThrow(() -> new RuntimeException("Support not found"));

        if (support.getRole() != Role.SUPPORT) {
            throw new RuntimeException("Only SUPPORT can assign tickets");
        }

        Ticket ticket = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getAssignedSupport() != null) {
            throw new RuntimeException("Ticket already assigned");
        }

        ticket.setAssignedSupport(support);
        ticket.setStatus(TicketStatus.UNDER_REVIEW);
        ticket.setUpdatedAt(LocalDateTime.now());

        return ticketRepo.save(ticket);
    }

    // ===============================
    // 3️⃣ RESOLVE TICKET
    // ===============================
    public Ticket resolveTicket(Long ticketId, String supportUsername) {

        User support = userRepo.findByUsername(supportUsername)
                .orElseThrow(() -> new RuntimeException("Support not found"));

        if (support.getRole() != Role.SUPPORT) {
            throw new RuntimeException("Only SUPPORT can resolve tickets");
        }

        Ticket ticket = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        // 🔄 CHANGED:
        // Earlier: error was thrown if already resolved
        // Now: idempotent behavior (safe if clicked twice)
        if (ticket.getStatus() == TicketStatus.RESOLVED) {
            return ticket; // 🔄 CHANGED
        }

        if (ticket.getAssignedSupport() != null &&
            !ticket.getAssignedSupport().getUserId().equals(support.getUserId())) {
            throw new RuntimeException("Ticket assigned to another support");
        }

        ticket.setAssignedSupport(support);
        ticket.setStatus(TicketStatus.RESOLVED);
        ticket.setUpdatedAt(LocalDateTime.now());

        return ticketRepo.save(ticket);
    }
}
