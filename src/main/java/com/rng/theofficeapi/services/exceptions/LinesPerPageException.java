package com.rng.theofficeapi.services.exceptions;

public class LinesPerPageException extends RuntimeException{

    public LinesPerPageException(){}

    public LinesPerPageException(String message){
        super(message);
    }
}