package com.springJourney.mock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutowiredTestService {
    @Autowired
    private Dependency1 dep1;
    @Autowired
    private Dependency2 dep2;

    public String complexMethod() {
        return dep1.method1() + dep2.method2();
    }
}