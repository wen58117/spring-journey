package com.springJourney.service;

import javax.annotation.PostConstruct;

public class MyService {
    private String message;

    @PostConstruct
    public void init() {
        this.message = "Initialized!";
        System.out.println("MyService initialized: " + message);
    }

    public void sayHello() {
        System.out.println(message);
    }
}