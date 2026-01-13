package com.company.supportsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.supportsystem.model.Ticket;
import com.company.supportsystem.model.TicketStatus;
import com.company.supportsystem.model.User;

public interface TicketRepo extends JpaRepository<Ticket, Long> {

    List<Ticket> findByMerchant(User merchant);

    List<Ticket> findByStatus(TicketStatus status);
}
