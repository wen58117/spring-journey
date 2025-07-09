package com.springJourney.lazy.config;

import com.springJourney.lazy.service.LazySingletonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class LazyAppConfig {
    @Bean
    @Lazy
    public LazySingletonService lazySingletonService() {
        return new LazySingletonService();
    }
}