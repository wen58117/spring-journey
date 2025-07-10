package com.springJourney.mock.service.impl;

import com.springJourney.mock.service.Dependency2;
import org.springframework.stereotype.Component;

@Component
public class Dependency2Impl implements Dependency2 {
    @Override
    public String method2() {
        return "method2";
    }
}
