package com.springJourney.service;

public class MyService {
    private final String name;

    public MyService(String name) {
        this.name = name;
    }

    public String sayHello() {
        return name + " 成功加载！";
    }
}