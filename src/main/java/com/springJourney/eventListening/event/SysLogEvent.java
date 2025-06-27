package com.springJourney.eventListening.event;

import com.springJourney.eventListening.entity.SysLogVo;
import org.springframework.context.ApplicationEvent;

public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(SysLogVo source) {
        super(source);
    }
}
