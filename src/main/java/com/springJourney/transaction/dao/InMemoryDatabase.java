package com.springJourney.transaction.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个简单的“内存数据库”模拟，支持快照和恢复，用于模拟事务隔离与回滚。
 */
public class InMemoryDatabase {

    /**
     * 当前生效的数据列表。
     */
    private List<String> records = new ArrayList<>();

    /**
     * 完全替换当前数据库状态，用于事务切换。
     */
    public void setRecords(List<String> newRecords) {
        this.records = newRecords;
    }

    /**
     * 向“表”中添加一条记录。
     */
    public void addRecord(String record) {
        System.out.println("数据库添加记录：" + record);
        records.add(record);
    }

    /**
     * 获取当前所有记录的副本（外部只读使用）。
     */
    public List<String> getRecords() {
        return new ArrayList<>(records);
    }

    /**
     * 创建当前状态的快照，用于之后的回滚。
     */
    public List<String> snapshot() {
        return new ArrayList<>(records);
    }

    /**
     * 将数据库状态恢复到指定快照。
     */
    public void restore(List<String> snapshot) {
        System.out.println("数据库恢复快照：" + snapshot);
        this.records = new ArrayList<>(snapshot);
    }
}
