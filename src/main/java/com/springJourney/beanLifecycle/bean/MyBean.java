package com.springJourney.beanLifecycle.bean;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MyBean implements InitializingBean, DisposableBean {

    public MyBean() {
        System.out.println("1️⃣ MyBean 构造方法调用");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("2️⃣ @PostConstruct 调用");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("3️⃣ InitializingBean.afterPropertiesSet 调用");
    }

    public void customInit() {
        System.out.println("4️⃣ 自定义初始化方法调用");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("7️⃣ @PreDestroy 调用");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("8️⃣ DisposableBean.destroy 调用");
    }

    public void customDestroy() {
        System.out.println("9️⃣ 自定义销毁方法调用");
    }
}
