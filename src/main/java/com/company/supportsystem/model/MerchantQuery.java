package com.company.supportsystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "merchant_queries")
public class MerchantQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // query_id

    private String merchantId;
    private String transactionId;
    private String transactionRefNo;
    private String mid;

    private LocalDateTime transactionDateAndTime;
    private Double transactionAmount;
    private String paymentType;

    private String customerName;
    private String customerEmail;

    private Double disputedAmount;
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ===== GETTERS & SETTERS =====

    public Long getId() { return id; }

    public String getMerchantId() { return merchantId; }
    public void setMerchantId(String merchantId) { this.merchantId = merchantId; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getTransactionRefNo() { return transactionRefNo; }
    public void setTransactionRefNo(String transactionRefNo) { this.transactionRefNo = transactionRefNo; }

    public String getMid() { return mid; }
    public void setMid(String mid) { this.mid = mid; }

    public LocalDateTime getTransactionDateAndTime() { return transactionDateAndTime; }
    public void setTransactionDateAndTime(LocalDateTime transactionDateAndTime) {
        this.transactionDateAndTime = transactionDateAndTime;
    }

    public Double getTransactionAmount() { return transactionAmount; }
    public void setTransactionAmount(Double transactionAmount) { this.transactionAmount = transactionAmount; }

    public String getPaymentType() { return paymentType; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public Double getDisputedAmount() { return disputedAmount; }
    public void setDisputedAmount(Double disputedAmount) { this.disputedAmount = disputedAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
