package com.rng.theofficeapi.services.exceptions;

public class AuthorizationException extends RuntimeException {

    public AuthorizationException() {}

    public AuthorizationException(String message) {
        super(message);
    }
}