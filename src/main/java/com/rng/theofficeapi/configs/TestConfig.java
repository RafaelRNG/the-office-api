package com.rng.theofficeapi.config;

import com.rng.theofficeapi.service.database.StartDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private StartDatabase startDatabase;

    @Bean
    public boolean StartDatabaseTest(){

        startDatabase.database();

        return true;
    }
}