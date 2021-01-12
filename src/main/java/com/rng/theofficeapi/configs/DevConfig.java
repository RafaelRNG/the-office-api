package com.rng.theofficeapi.configs;

import com.rng.theofficeapi.service.database.StartDatabase;
import com.rng.theofficeapi.services.email.EmailService;
import com.rng.theofficeapi.services.email.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private StartDatabase startDatabase;

    @Bean
    public boolean insertDatabase(){
        startDatabase.database();

        return true;
    }

    @Bean
    public EmailService sendEmail() {
        return new SmtpEmailService();
    }
}