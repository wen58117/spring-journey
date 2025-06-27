package com.springJourney.idempotent.config;

import com.springJourney.idempotent.redis.MockRedisTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockRedisConfig {

    @Bean
    public MockRedisTemplate mockRedisTemplate() {
        return new MockRedisTemplate();
    }
}