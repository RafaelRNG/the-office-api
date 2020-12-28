package com.rng.theofficeapi.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddressDTO {

    private Long id;

    @NotNull(message = "null is not allowed")
    @NotEmpty(message = "empty value is not allowed")
    private String street;

    @NotNull(message = "null is not allowed")
    @NotEmpty(message = "empty value is not allowed")
    private String number;

    @NotNull(message = "null is not allowed")
    @NotEmpty(message = "empty value is not allowed")
    private String neighborhood;

    @NotNull(message = "null is not allowed")
    @NotEmpty(message = "empty value is not allowed")
    private String city;

    @NotNull(message = "null is not allowed")
    @NotEmpty(message = "empty value is not allowed")
    private String state;

    @NotNull(message = "null is not allowed")
    @NotEmpty(message = "empty value is not allowed")
    private String complement;

    @NotNull(message = "null is not allowed")
    private Long Client;

    public AddressDTO(Long id, String street, String number, String neighborhood, String city, String state, String complement, Long client) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.complement = complement;
        this.Client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Long getClient() {
        return Client;
    }

    public void setClient(Long client) {
        Client = client;
    }
}