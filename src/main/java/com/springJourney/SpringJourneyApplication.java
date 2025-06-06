package com.springJourney;

import com.springJourney.service.MyServiceInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringJourneyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringJourneyApplication.class, args);
        MyServiceInit serviceInit = context.getBean(MyServiceInit.class);
        serviceInit.sayHello();
    }

}
