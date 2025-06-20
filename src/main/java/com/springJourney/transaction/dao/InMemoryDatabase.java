package com.springJourney.transaction.dao;

import com.springJourney.transaction.config.TransactionContextHolder;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase {
    private final List<String> records = new ArrayList<>();
    private final List<OperationLog> opLogs = new ArrayList<>();

    /**
     * 添加记录，并记录操作日志（包括是否来自新事务）
     */
    public void addRecord(String record) {
        boolean isFromNewTransaction = TransactionContextHolder.isNewTransaction();
        System.out.println("数据库插入记录：" + record + " 来自新事务：" + isFromNewTransaction);
        records.add(record);
        opLogs.add(new OperationLog(OperationLog.OperationType.INSERT, record, isFromNewTransaction));
    }

    /**
     * 获取当前数据库记录
     */
    public List<String> getRecords() {
        return new ArrayList<>(records);
    }

    /**
     * 回滚操作：如果是主事务回滚（isNewTransaction = false），则回滚所有；如果是子事务回滚（isNewTransaction = true），则只回滚来自子事务的操作。
     */
    public void rollback(boolean isNewTransaction) {
        System.out.println("开始回滚 → 仅回滚新事务操作？" + isNewTransaction);
        for (int i = opLogs.size() - 1; i >= 0; i--) {
            OperationLog log = opLogs.get(i);
            // 回滚条件：主事务回滚全部，子事务只回滚来自新事务的
            if (log.getType() == OperationLog.OperationType.INSERT &&
                    (isNewTransaction == log.isFromNewTransaction())) {
                records.remove(log.getRecord());
                opLogs.remove(i);
            }
        }
    }

    /**
     * 提交成功的新事务操作，从日志中移除，避免被主事务回滚
     */
    public void clearNewTransactionLogs() {
        opLogs.removeIf(OperationLog::isFromNewTransaction);
    }
}
