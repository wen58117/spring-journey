package com.springJourney.transaction.config;

public class TransactionContextHolder {
    private static final ThreadLocal<Boolean> isNewTransaction = new ThreadLocal<>();

    public static void setIsNewTransaction(boolean isNew) {
        isNewTransaction.set(isNew);
    }

    public static boolean isNewTransaction() {
        Boolean val = isNewTransaction.get();
        return val != null && val;
    }

    public static void clear() {
        isNewTransaction.remove();
    }
}
