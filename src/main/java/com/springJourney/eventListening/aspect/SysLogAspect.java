package com.springJourney.eventListening.aspect;

import com.alibaba.fastjson2.JSON;
import com.springJourney.eventListening.annotation.even.SysLog;
import com.springJourney.eventListening.entity.SysLogVo;
import com.springJourney.eventListening.event.SysLogEvent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class SysLogAspect {

    private final ApplicationContext context;

    // 通过构造函数注入 ApplicationContext
    public SysLogAspect(ApplicationContext context) {
        this.context = context;
    }


    @Around("@annotation(sysLog)")
    public Object around(ProceedingJoinPoint point, SysLog sysLog) {
        //类名
        String className = point.getTarget().getClass().getName();
        //方法名
        String methodName = point.getSignature().getName();
        SysLogVo sysLogVo = new SysLogVo();
        sysLogVo.setClassName(className);
        sysLogVo.setMethodName(methodName);
        sysLogVo.setTitle(sysLog.value());
        Object rep;
        try {
            sysLogVo.setRequestParam(JSON.toJSONString(point.getArgs()));
            rep = point.proceed();
            sysLogVo.setResponseParam(JSON.toJSONString(rep));
        } catch (Throwable e){
            throw new RuntimeException(e);
        }finally {
            context.publishEvent(new SysLogEvent(sysLogVo));
        }
        return rep;
    }
}
