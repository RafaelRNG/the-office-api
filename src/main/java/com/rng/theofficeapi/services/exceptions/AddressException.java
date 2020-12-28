package com.rng.theofficeapi.services.exceptions;

public class AddressException extends RuntimeException{

    public AddressException(){}

    public AddressException(String message){
        super(message);
    }
}
