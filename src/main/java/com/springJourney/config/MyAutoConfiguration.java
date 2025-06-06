package com.springJourney.config;

import com.springJourney.service.MyServiceInit;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class MyAutoConfiguration {
    @Bean
    public MyServiceInit myService() {
        return new MyServiceInit();
    }
}