package com.rng.theofficeapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rng.theofficeapi.entities.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO {

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date;
    private Long client;
    private Long salesman;
    private Address address;
    private Payment payment;

    private List<OrderItem> products = new ArrayList<>();

    public OrderDTO(){}

    public OrderDTO(Long id, Date date, Client client, Salesman salesman, Address address) {
        this.id = id;
        this.date = date;
        this.client = client.getId();
        this.salesman = salesman.getId();
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Long getSalesman() {
        return salesman;
    }

    public void setSalesman(Long salesman) {
        this.salesman = salesman;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<OrderItem> getProducts() {
        return products;
    }

    public void setProducts(List<OrderItem> products) {
        this.products = products;
    }
}