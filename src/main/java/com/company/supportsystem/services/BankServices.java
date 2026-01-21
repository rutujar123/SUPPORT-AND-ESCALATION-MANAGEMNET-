package com.company.supportsystem.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.company.supportsystem.dto.BankEscalationRequest;
import com.company.supportsystem.model.BankEscalation;
import com.company.supportsystem.model.Ticket;
import com.company.supportsystem.model.TicketStatus;
import com.company.supportsystem.repository.BankEscalationRepo;
import com.company.supportsystem.repository.TicketRepo;

@Service
public class BankServices {

    private final TicketRepo ticketRepo;
    private final BankEscalationRepo bankEscalationRepo;
   

    public BankServices(TicketRepo ticketRepo,
                        BankEscalationRepo bankEscalationRepo
                       ) {
        this.ticketRepo = ticketRepo;
        this.bankEscalationRepo = bankEscalationRepo;
       
    }

    public void submitToBank(BankEscalationRequest request) {

        if (request.getBankEmail() == null || request.getBankEmail().isBlank()) {
            throw new RuntimeException("Bank email is required");
        }

        Ticket ticket = ticketRepo.findById(request.getTicketId())
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getStatus() == TicketStatus.SENT_TO_BANK) {
            throw new RuntimeException("Ticket already sent to bank");
        }

        if (ticket.getStatus() == TicketStatus.RESOLVED) {
            throw new RuntimeException("Resolved ticket cannot be sent to bank");
        }

        if (bankEscalationRepo.existsByTicket(ticket)) {
            throw new RuntimeException("Bank escalation already exists for this ticket");
        }

        BankEscalation escalation = new BankEscalation();
        escalation.setTicket(ticket);
        escalation.setProcessorId(request.getProcessorId());
        escalation.setProcessorName(request.getProcessorName());
        escalation.setTransactionId(request.getTransactionId());
        escalation.setTransactionDate(request.getTransactionDate());
        escalation.setBankEmail(request.getBankEmail());
        escalation.setCreatedAt(LocalDateTime.now());

        bankEscalationRepo.save(escalation);

        ticket.setStatus(TicketStatus.SENT_TO_BANK);
        ticket.setUpdatedAt(LocalDateTime.now());
        ticketRepo.save(ticket);

        // Mail must not break DB
        
    }
    public BankEscalation getByTicketId(Long ticketId) {
        return bankEscalationRepo.findByTicket_TicketId(ticketId)
                .orElseThrow(() -> new RuntimeException("Bank escalation not found"));
    }
}
