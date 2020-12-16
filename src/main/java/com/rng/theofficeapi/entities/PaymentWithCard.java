package com.rng.theofficeapi.entities;

import com.rng.theofficeapi.entities.enums.PaymentStatus;

import javax.persistence.Entity;

@Entity
public class PaymentWithCard extends Payment{

    private static final long serialVersionUID = 1L;

    private String payment = "Card";

    private Long installments;

    public PaymentWithCard(){}

    public PaymentWithCard(Long id, PaymentStatus paymentStatus, Order order, Long installments) {
        super(id, paymentStatus, order);
        this.installments = installments;
    }

    public String getPayment() {
        return payment;
    }

    public Long getInstallments() {
        return installments;
    }

    public void setInstallments(Long installments) {
        this.installments = installments;
    }
}