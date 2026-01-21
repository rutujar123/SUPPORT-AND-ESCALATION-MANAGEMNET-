package com.company.supportsystem.services;

import org.springframework.stereotype.Service;

import com.company.supportsystem.dto.BankEmailDraftDTO;
import com.company.supportsystem.model.BankEscalation;
import com.company.supportsystem.model.Ticket;

@Service
public class BankDraftService {

    public BankEmailDraftDTO generateDraft(Ticket ticket, BankEscalation escalation) {

        BankEmailDraftDTO dto = new BankEmailDraftDTO();

        dto.setTo(escalation.getBankEmail());

        dto.setSubject(
            "Transaction Dispute Escalation | Ticket #" + ticket.getTicketId()
        );

        dto.setBody(
            "Dear Bank Team,\n\n" +
            "A transaction dispute has been escalated.\n\n" +
            "Ticket ID: " + ticket.getTicketId() + "\n" +
            "Processor: " + escalation.getProcessorName() + "\n" +
            "Processor ID: " + escalation.getProcessorId() + "\n" +
            "Transaction ID: " + escalation.getTransactionId() + "\n" +
            "Transaction Date: " + escalation.getTransactionDate() + "\n" +
            "Disputed Amount: " + ticket.getDisputedAmount() + "\n\n" +
            "Please review and respond.\n\n" +
            "Regards,\nSupport Team"
        );

        return dto;
    }
}
