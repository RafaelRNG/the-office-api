package com.rng.theofficeapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rng.theofficeapi.entities.enums.PaymentStatus;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PaymentWithBillet extends Payment {

    private static final long serialVersionUID = 1L;

    private String payment = "Billet";

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date payday;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dueDate;

    public PaymentWithBillet(){}

    public PaymentWithBillet(Long id, PaymentStatus paymentStatus, Order order, Date payday, Date dueDate){
        super(id, paymentStatus, order);
        this.payday = payday;
        this.dueDate = dueDate;
    }

    public String getPayment() {
        return payment;
    }

    public Date getPayday() {
        return payday;
    }

    public void setPayday(Date payday) {
        this.payday = payday;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}