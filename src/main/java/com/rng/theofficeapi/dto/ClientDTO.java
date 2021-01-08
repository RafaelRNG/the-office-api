package com.rng.theofficeapi.dto;

import com.rng.theofficeapi.entities.Address;
import com.rng.theofficeapi.entities.Order;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ClientDTO {

    private Long id;

    @NotNull(message = "null is not allowed")
    @NotEmpty(message = "empty value is not allowed")
    private String name;

    @CNPJ(message = "invalid Brazilian corporate taxpayer registration number (CNPJ)")
    private String cnpj;

    @Email(message = "must be a well-formed email address")
    private String email;

    @NotNull(message = "null is not allowed")
    private String cellPhone;

    private Address address;

    private List<Order> orders = new ArrayList<>();

    public ClientDTO(){}

    public ClientDTO(Long id, String name, String cnpj, String email, String cellPhone, Address address) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.email = email;
        this.cellPhone = cellPhone;
        this.address = address;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}