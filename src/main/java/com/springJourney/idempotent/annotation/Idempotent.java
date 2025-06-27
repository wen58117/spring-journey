package com.springJourney.idempotent.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {
    String key() default ""; // 支持 SpEL 表达式，用于指定幂等参数
    int expireTime() default 60; // 幂等 key 的过期时间，单位：秒
}