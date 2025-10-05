package com.example.lowleveldesign.ParkingLotApp.dto;

import com.example.lowleveldesign.ParkingLotApp.model.enums.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentResponse {
    private Long paymentId;
    private double amount;
    private LocalDateTime timestamp;
    private PaymentStatus status;

    public PaymentResponse() {}

    public PaymentResponse(Long paymentId, double amount, LocalDateTime timestamp, PaymentStatus status) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
    }

    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }
}
