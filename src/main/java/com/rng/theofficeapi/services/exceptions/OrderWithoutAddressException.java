package com.rng.theofficeapi.services.exceptions;

public class OrderWithoutAddressException extends RuntimeException {

    public OrderWithoutAddressException(){}

    public OrderWithoutAddressException(String message){
        super(message);
    }
}
