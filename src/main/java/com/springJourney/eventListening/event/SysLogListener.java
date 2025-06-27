package com.springJourney.eventListening.event;

import com.springJourney.eventListening.entity.SysLogVo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SysLogListener {

    @Async
    @EventListener(SysLogEvent.class)
    @SneakyThrows
    public void listen(SysLogEvent event) {
        Thread.sleep(3000);
        SysLogVo source = (SysLogVo) event.getSource();
        // 处理日志
        log.info("============监听事件开始===================");
        log.info(String.valueOf(source));
        log.info("============监听事件结束===================");
    }
}
