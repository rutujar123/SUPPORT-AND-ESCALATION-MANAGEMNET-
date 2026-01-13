package com.company.supportsystem.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.company.supportsystem.model.Role;
import com.company.supportsystem.model.Ticket;
import com.company.supportsystem.model.TicketStatus;
import com.company.supportsystem.model.User;
import com.company.supportsystem.repository.TicketRepo;
import com.company.supportsystem.repository.UserRepo;

@Service
public class SupportTicketService {

	private final TicketRepo ticketRepo;
	private final UserRepo userRepo;
	
	public SupportTicketService(TicketRepo ticketRepo,UserRepo userRepo)
	{
		this.ticketRepo=ticketRepo;
		this.userRepo=userRepo;
	}
	public Ticket resolveTicket(Long ticketId,String supportUsername)
	{
		 User support = userRepo.findByUsername(supportUsername)
	                .orElseThrow(() -> new RuntimeException("Support not found"));
	
	if(support.getRole()!=Role.SUPPORT)
    {
		throw new RuntimeException("support can resolve ticket");
    }
	Ticket ticket = ticketRepo.findById(ticketId)
            .orElseThrow(() -> new RuntimeException("Ticket not found"));
	ticket.setStatus(TicketStatus.RESOLVED);
	ticket.setAssignedSupport(support);
	ticket.setUpdatedAt(LocalDateTime.now());
	
	return ticketRepo.save(ticket);
		
	}
	}
