package com.springJourney.eventListening.service;

import java.util.Map;

public interface EventService {

    Map<String,Object> publishEvent(Map<String,Object> input);
}
