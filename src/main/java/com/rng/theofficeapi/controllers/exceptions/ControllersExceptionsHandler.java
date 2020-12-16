package com.rng.theofficeapi.controllers.exceptions;

import com.rng.theofficeapi.services.exceptions.LinesPerPageException;
import com.rng.theofficeapi.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}