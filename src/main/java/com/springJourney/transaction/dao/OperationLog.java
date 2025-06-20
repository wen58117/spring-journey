package com.springJourney.transaction.dao;

public class OperationLog {
    public enum OperationType { INSERT, DELETE, UPDATE }

    private final OperationType type;
    private final String record;
    private final boolean fromNewTransaction;

    public OperationLog(OperationType type, String record, boolean fromNewTransaction) {
        this.type = type;
        this.record = record;
        this.fromNewTransaction = fromNewTransaction;
    }

    public OperationType getType() {
        return type;
    }

    public String getRecord() {
        return record;
    }

    public boolean isFromNewTransaction() {
        return fromNewTransaction;
    }
}
