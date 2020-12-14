package com.rng.theofficeapi.entities.enums;

public enum PaymentStatus {

    PENDING(1, "PENDING"),
    PAID(2, "PAID"),
    CANCELED(3, "CANCELED");

    private Integer code;
    private String title;

    private PaymentStatus(Integer code, String title){
        this.code = code;
        this.title = title;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static PaymentStatus toEnum(Integer code) {
        if (code == null) return null;

        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (code.equals(paymentStatus.getCode())) return paymentStatus;
        }

        throw new IllegalArgumentException("Invalid ID: " + code);
    }
}