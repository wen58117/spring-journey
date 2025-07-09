package com.springJourney.lazy.config;

import com.springJourney.lazy.service.LazySingletonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EagerAppConfig {
    @Bean
    public LazySingletonService lazySingletonService() {
        return new LazySingletonService();
    }
}