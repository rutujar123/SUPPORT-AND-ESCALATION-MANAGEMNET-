package com.company.supportsystem.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    // ===== BASIC DETAILS =====
    private String utr;
    private String orderId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime transactionDateandTime;

    private BigDecimal transactionamount;
    private String paymentType;
    private String customerName;
    private String customerEmail;
    private BigDecimal disputedAmount;
    private String reason;

    // ===== RELATIONS =====
    @ManyToOne
    @JoinColumn(name = "merchant_id", nullable = false)
    private User merchant;

    @ManyToOne
    @JoinColumn(name = "assigned_support_id")
    private User assignedSupport;

    // ===== STATUS & META =====
    @Enumerated(EnumType.STRING)
     @Column(nullable = false)
    private TicketStatus status;


    private String attachmentPath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ===== GETTERS & SETTERS =====

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getUtr() {
        return utr;
    }

    public void setUtr(String utr) {
        this.utr = utr;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getTransactionDateandTime() {
        return transactionDateandTime;
    }

    public void setTransactionDateandTime(LocalDateTime transactionDateandTime) {
        this.transactionDateandTime = transactionDateandTime;
    }

    public BigDecimal getTransactionamount() {
        return transactionamount;
    }

    public void setTransactionamount(BigDecimal transactionamount) {
        this.transactionamount = transactionamount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public BigDecimal getDisputedAmount() {
        return disputedAmount;
    }

    public void setDisputedAmount(BigDecimal disputedAmount) {
        this.disputedAmount = disputedAmount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public User getMerchant() {
        return merchant;
    }

    public void setMerchant(User merchant) {
        this.merchant = merchant;
    }

    public User getAssignedSupport() {
        return assignedSupport;
    }

    public void setAssignedSupport(User assignedSupport) {
        this.assignedSupport = assignedSupport;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
