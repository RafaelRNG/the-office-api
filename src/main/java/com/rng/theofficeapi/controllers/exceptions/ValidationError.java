package com.rng.theofficeapi.controllers.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationError extends ErrorMessageFormat {

    private List<ValidationMessage> validationMessages = new ArrayList<>();

    public ValidationError(Integer status, String message, Date date) {
        super(status, message, date);
    }

    public List<ValidationMessage> getValidationMessages() {
        return validationMessages;
    }

    public void addError(ValidationMessage validationMessage){
        this.validationMessages.add(validationMessage);
    }
}