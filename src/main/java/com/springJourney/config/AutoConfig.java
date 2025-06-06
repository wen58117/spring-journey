package com.springJourney.config;

import com.springJourney.service.MyService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class AutoConfig {
    @Bean
    public MyService autoService() {
        return new MyService("AutoConfig");
    }
}