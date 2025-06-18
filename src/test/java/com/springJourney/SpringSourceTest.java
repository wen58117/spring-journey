package com.springJourney;

import com.springJourney.config.spring.AppConfig;
import com.springJourney.service.MySpringService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringSourceTest {

    @Test
    public void testSpring() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MySpringService myService = context.getBean(MySpringService.class);
        myService.doSomething();
        context.close();
    }

}