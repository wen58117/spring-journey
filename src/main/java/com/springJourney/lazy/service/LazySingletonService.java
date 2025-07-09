package com.springJourney.lazy.service;

public class LazySingletonService {
    public LazySingletonService() {
        System.out.println("LazySingletonService 实例被创建");
    }

    public void doSomething() {
        System.out.println("LazySingletonService 执行操作");
    }
}