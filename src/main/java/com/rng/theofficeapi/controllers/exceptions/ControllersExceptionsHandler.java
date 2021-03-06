package com.rng.theofficeapi.controllers.exceptions;

import com.rng.theofficeapi.services.exceptions.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllersExceptionsHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorMessageFormat> notFoundException(){
        ErrorMessageFormat errorMessageFormat = new ErrorMessageFormat(HttpStatus.NOT_FOUND.value(), "Object not found", new Date());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessageFormat);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessageFormat> dataIntegrityException(){
        ErrorMessageFormat errorMessageFormat = new ErrorMessageFormat(HttpStatus.BAD_REQUEST.value(), "There is a relationship with this object", new Date());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageFormat);
    }

    @ExceptionHandler(LinesPerPageException.class)
    public ResponseEntity<ErrorMessageFormat> linesPerPageException(){
        ErrorMessageFormat errorMessageFormat = new ErrorMessageFormat(HttpStatus.BAD_REQUEST.value(), "Zero value is not possible for lines per page", new Date());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageFormat);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageFormat> methodArgumentNotValidException(MethodArgumentNotValidException notValidException){
        ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Error in evaluations", new Date());

        for(FieldError message: notValidException.getBindingResult().getFieldErrors()){
            validationError.addError(new ValidationMessage(message.getField(), message.getDefaultMessage()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

    @ExceptionHandler(AddressException.class)
    public ResponseEntity<ErrorMessageFormat> addressException(){
        ErrorMessageFormat errorMessageFormat = new ErrorMessageFormat(HttpStatus.BAD_REQUEST.value(), "This customer already has an address, says a change or assigns to another customer", new Date());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageFormat);
    }

    @ExceptionHandler(OrderWithoutAddressException.class)
    public ResponseEntity<ErrorMessageFormat> orderWithoutAddress() {
        ErrorMessageFormat errorMessageFormat = new ErrorMessageFormat(HttpStatus.BAD_REQUEST.value(), "order address is required", new Date());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageFormat);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorMessageFormat> authorizationException() {
        ErrorMessageFormat errorMessageFormat = new ErrorMessageFormat(HttpStatus.FORBIDDEN.value(), "Not Authorized", new Date());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessageFormat);
    }
}