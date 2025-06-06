package com.springJourney.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Slf4j
public class MyServiceInit {
    private static final Logger logger = LoggerFactory.getLogger(MyServiceInit.class);

    private String message;

    @PostConstruct
    public void init() {
        this.message = "Initialized!";
        logger.info("MyServiceInit initialized: {}", message);
    }

    public void sayHello() {
        log.info(message);
    }
}