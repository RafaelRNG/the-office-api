package com.rng.theofficeapi.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ErrorMessageFormat {

    private Integer status;
    private String message;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date;

    public ErrorMessageFormat(){}

    public ErrorMessageFormat(Integer status, String message, Date date) {
        this.status = status;
        this.message = message;
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
