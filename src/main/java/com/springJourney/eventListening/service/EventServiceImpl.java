package com.springJourney.eventListening.service;

import com.springJourney.eventListening.annotation.even.SysLog;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EventServiceImpl implements EventService {

    @Override
    @SysLog("测试日志")
    public Map<String, Object> publishEvent(Map<String, Object> input) {
        Map<String, Object> map = new HashMap<>();
        map.put("输出a", "11");
        map.put("输出b", "22");
        map.put("输出c", "33");
        return map;
    }
}
