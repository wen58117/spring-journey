package com.springJourney.transaction.config;

import com.springJourney.transaction.dao.InMemoryDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.springJourney.transaction.service")
public class TransactionConfig {

    @Bean
    public InMemoryDatabase inMemoryDatabase() {
        return new InMemoryDatabase();
    }

    @Bean
    public PlatformTransactionManager transactionManager(InMemoryDatabase database) {
        return new MockTransactionManager(database);
    }

}
