package com.rng.theofficeapi.services.email;

import com.rng.theofficeapi.entities.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${sender.default}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Order order) {
        SimpleMailMessage simpleMailMessage = prepareSimpleEmailMessageFromOrder(order);
        sendEmail(simpleMailMessage);
    }

    public SimpleMailMessage prepareSimpleEmailMessageFromOrder(Order order) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(order.getClient().getEmail());
        simpleMailMessage.setText("Order successfully placed");
        simpleMailMessage.setSentDate(new Date());

        return simpleMailMessage;
    }
}