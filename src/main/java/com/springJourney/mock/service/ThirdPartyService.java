package com.springJourney.mock.service;

import java.io.IOException;

public class ThirdPartyService {
    public String getData() throws IOException {
        // 这里是实际调用第三方接口的逻辑，测试时会被 Mock
        throw new UnsupportedOperationException("This should be implemented for real API call");
    }
}