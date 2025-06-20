package com.springJourney.transaction.service;

import com.springJourney.transaction.dao.InMemoryDatabase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecondaryService {
    private final InMemoryDatabase database;

    public SecondaryService(InMemoryDatabase database) {
        this.database = database;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void executeWithTransactionAndException2(String record) {
        System.out.println("执行事务方法，添加记录(开启新事物)：" + record);
        database.addRecord(record);
    }
}