package com.rng.theofficeapi.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.rng.theofficeapi.entities.enums.PaymentStatus;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_payment_with_card")
@JsonTypeName("paymentWithCard")
public class PaymentWithCard extends Payment {

    private static final long serialVersionUID = 1L;

    private Long installments;

    public PaymentWithCard(){}

    public PaymentWithCard(Long id, PaymentStatus paymentStatus, Order order, Long installments) {
        super(id, paymentStatus, order);
        this.installments = installments;
    }

    public Long getInstallments() {
        return installments;
    }

    public void setInstallments(Long installments) {
        this.installments = installments;
    }
}