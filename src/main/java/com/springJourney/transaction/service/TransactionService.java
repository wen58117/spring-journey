package com.springJourney.transaction.service;

import com.springJourney.transaction.dao.InMemoryDatabase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {
    private final InMemoryDatabase database;
    private final SecondaryService secondaryService;

    public TransactionService(InMemoryDatabase database, SecondaryService secondaryService) {
        this.database = database;
        this.secondaryService = secondaryService;
    }

    @Transactional
    public void executeWithTransaction(String record) {
        System.out.println("执行事务方法，添加记录：" + record);
        database.addRecord(record);
    }

    @Transactional
    public void executeWithTransactionAndException(String record) {
        System.out.println("执行事务方法，添加记录（将抛出异常）：" + record);
        database.addRecord(record);
        throw new RuntimeException("模拟异常");
    }

    @Transactional
    public void executeWithTransactionAndException2(String record) {
        System.out.println("执行事务方法，添加记录（将抛出异常）：" + record);
        database.addRecord(record);
        secondaryService.executeWithTransactionAndException2("记录21");
        throw new RuntimeException("模拟异常");
    }

    public List<String> getData() {
        return database.getRecords();
    }
}
