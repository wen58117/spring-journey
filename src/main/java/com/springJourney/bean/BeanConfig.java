package com.springJourney.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    /**
     * 这里可以将未被@Service之类的注解标记的，未被Spring容器管理的Bean交给Spring容器管理
     * 这样调用方就可以使用@Autowired注入这个Bean
     * 未被Spring容器管理的Bean无法直接通过@Autowired注入
     */
    @Bean
    public BeanTestService beanTestService() {
        return new BeanTestService();
    }
}
