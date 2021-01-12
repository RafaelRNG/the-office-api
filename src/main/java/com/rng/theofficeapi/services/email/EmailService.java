package com.rng.theofficeapi.services.email;

import com.rng.theofficeapi.entities.Order;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

   void sendOrderConfirmationEmail(Order order);

    void sendEmail(SimpleMailMessage simpleMailMessage);
}