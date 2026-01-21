package com.company.supportsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.supportsystem.model.BankEscalation;
import com.company.supportsystem.model.Ticket;

public interface BankEscalationRepo extends JpaRepository<BankEscalation, Long> {

    boolean existsByTicket(Ticket ticket);
    Optional<BankEscalation> findByTicket_TicketId(Long ticketId);
}
