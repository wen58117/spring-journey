package com.springJourney.eventListening;

import com.springJourney.eventListening.service.EventService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@EnableAspectJAutoProxy // 启用AspectJ代理
@EnableAsync // 启用异步支持
@Slf4j
public class EventListenerTest {

    @Autowired
    private EventService eventService;

    @Test
    @SneakyThrows // 忽略异常处理，简化代码
    public void test() {
        Map<String, Object> map = new HashMap<>();
        map.put("输入a", "11");
        map.put("输入b", "22");
        map.put("输入c", "33");
        // 模拟调用事件发布方法
        Map<String, Object> map1 = eventService.publishEvent(map);
        log.info("模拟调用事件发布方法结束");
        Thread.sleep(100000);
    }
}
