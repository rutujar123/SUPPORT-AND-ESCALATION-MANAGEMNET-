package com.company.supportsystem.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.company.supportsystem.model.BankEscalation;
import com.company.supportsystem.model.Ticket;

@Service
public class BankMailService {

    private final JavaMailSender mailSender;

    public BankMailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendToBank(Ticket ticket, BankEscalation escalation) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(escalation.getBankEmail());
        message.setSubject("Transaction Dispute Escalation | Ticket #" + ticket.getTicketId());

        message.setText(
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

        mailSender.send(message);
    }
}
