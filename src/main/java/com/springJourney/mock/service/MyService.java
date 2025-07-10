package com.springJourney.mock.service;

import java.io.IOException;

public class MyService {

    private final ThirdPartyService thirdPartyService;

    public MyService(ThirdPartyService thirdPartyService) {
        this.thirdPartyService = thirdPartyService;
    }

    public String fetchData() throws IOException {
        return thirdPartyService.getData();
    }
}