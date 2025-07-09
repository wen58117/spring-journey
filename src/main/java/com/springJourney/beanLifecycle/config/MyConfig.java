package com.springJourney.beanLifecycle.config;

import com.springJourney.beanLifecycle.bean.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.springJourney.beanLifecycle"}) //不指定扫描的包路径就必须在配置类中定义注册方法
public class MyConfig {

    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public MyBean myBean() {
        return new MyBean();
    }

//    @Bean
//    public MyBeanPostProcessor myBeanPostProcessor() {
//        return new MyBeanPostProcessor();
//    }
}
