package com.springJourney;

import com.springJourney.bean.BeanConfig;
import com.springJourney.bean.BeanTestService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanTest {

    @Test
    public void testSpring() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        BeanTestService myService = context.getBean(BeanTestService.class);
        myService.doSomething();
        context.close();
    }
}
