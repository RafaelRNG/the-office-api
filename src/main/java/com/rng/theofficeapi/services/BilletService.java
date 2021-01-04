package com.rng.theofficeapi.services;

import com.rng.theofficeapi.entities.Payment;
import com.rng.theofficeapi.entities.PaymentWithBillet;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BilletService {

    public void fillPaymentWithBillet(PaymentWithBillet payment, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        payment.setDueDate(calendar.getTime());
    }
}