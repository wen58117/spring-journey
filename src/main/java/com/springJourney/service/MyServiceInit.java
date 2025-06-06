package com.springJourney.service;

import javax.annotation.PostConstruct;

public class MyServiceInit {
    private String message;

    @PostConstruct
    public void init() {
        this.message = "Initialized!";
        System.out.println("MyServiceInit initialized: " + message);
    }

    public void sayHello() {
        System.out.println(message);
    }
}