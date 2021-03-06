package com.rng.theofficeapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.rng.theofficeapi.entities.enums.PaymentStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_abstract_payment")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private Integer paymentStatus;

    @JsonIgnore
    @MapsId
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Payment() {}

    public Payment(Long id, PaymentStatus paymentStatus, Order order) {
        this.id = id;
        this.paymentStatus = paymentStatus.getCode();
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentStatus getPaymentStatus() {
        return PaymentStatus.toEnum(this.paymentStatus);
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus.getCode();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Objects.equals(getId(), payment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}