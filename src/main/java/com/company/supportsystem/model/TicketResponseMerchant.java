package com.company.supportsystem.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket_response")
public class TicketResponseMerchant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long response_id;

	public long getResponse_id() {
		return response_id;
	}

	public void setResponse_id(long response_id) {
		this.response_id = response_id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public User getSupport() {
		return support;
	}

	public void setSupport(User support) {
		this.support = support;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@ManyToOne
	@JoinColumn(name = "ticket_id", nullable = false)
	private Ticket ticket;

	@ManyToOne
	@JoinColumn(name = "support_id", nullable = false)
	private User support;

	@Column(nullable = false)
	private String message;

	private String attachmentPath;

	private LocalDateTime createdAt;

}
