package com.rng.theofficeapi.dto;

import com.rng.theofficeapi.entities.Client;
import com.rng.theofficeapi.entities.Order;
import com.sun.istack.Nullable;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SalesmanDTO {

    private Long id;

    @NotEmpty(message = "null values are not possible")
    @Length(min = 3, max = 120, message = "only name between 3 and 120 characters")
    private String name;

    @NotEmpty(message = "null values are not possible")
    @NotNull(message = "null is not allowed")
    @Length(min = 3, max = 120, message = "only products between 3 and 120 characters")
    private String identification;

    private List<Order> orders = new ArrayList<>();

    public SalesmanDTO(Long id, String name, String identification, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.identification = identification;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Client getCustomerRequest(){
        for(Order order : this.orders){
            return order.getClient();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalesmanDTO)) return false;
        SalesmanDTO that = (SalesmanDTO) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
