package com.springJourney.mock.service.impl;

import com.springJourney.mock.service.Dependency1;
import org.springframework.stereotype.Component;

@Component
public class Dependency1Impl implements Dependency1 {
    @Override
    public String method1() {
        return "method1";
    }
}
