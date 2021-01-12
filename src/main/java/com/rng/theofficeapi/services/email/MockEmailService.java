package com.rng.theofficeapi.services.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        LOGGER.info("SENDING EMAIL");
        LOGGER.info("order successfully placed..");
        LOGGER.info("Email sent");
    }
}