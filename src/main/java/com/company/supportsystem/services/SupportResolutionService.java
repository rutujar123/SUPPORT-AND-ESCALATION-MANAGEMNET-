package com.company.supportsystem.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.company.supportsystem.model.Role;
import com.company.supportsystem.model.Ticket;
import com.company.supportsystem.model.TicketResponseMerchant;
import com.company.supportsystem.model.TicketStatus;
import com.company.supportsystem.model.User;
import com.company.supportsystem.repository.TicketRepo;
import com.company.supportsystem.repository.TicketResponseMerchantRepo;
import com.company.supportsystem.repository.UserRepo;

@Service
public class SupportResolutionService {

    private final TicketRepo ticketRepo;
    private final UserRepo userRepo;
    private final TicketResponseMerchantRepo responseRepo;

    public SupportResolutionService(
            TicketRepo ticketRepo,
            UserRepo userRepo,
            TicketResponseMerchantRepo responseRepo) {

        this.ticketRepo = ticketRepo;
        this.userRepo = userRepo;
        this.responseRepo = responseRepo;
    }

    public void sendResolutionToMerchant(
            Long ticketId,
            String supportUsername,
            String message,
            MultipartFile attachment) {

        User support = userRepo.findByUsername(supportUsername)
                .orElseThrow(() -> new RuntimeException("Support not found"));

        if (support.getRole() != Role.SUPPORT) {
            throw new RuntimeException("Only support can send resolution");
        }

        Ticket ticket = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        // ✅ Ticket MUST be RESOLVED before replying to merchant
        if (ticket.getStatus() != TicketStatus.RESOLVED) {
            throw new RuntimeException("Resolve ticket first before sending message to merchant");
        }

        // ---- Save message for merchant ----
        TicketResponseMerchant response = new TicketResponseMerchant();
        response.setTicket(ticket);
        response.setSupport(support);
        response.setMessage(message);
        response.setCreatedAt(LocalDateTime.now());

        if (attachment != null && !attachment.isEmpty()) {
            String path = "uploads/" + attachment.getOriginalFilename();
            response.setAttachmentPath(path);
        }

        responseRepo.save(response);

        
    }
}
