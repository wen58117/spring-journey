package com.springJourney.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("com.springJourney.service")
public class AppConfig {
}
