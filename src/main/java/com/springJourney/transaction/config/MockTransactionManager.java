package com.springJourney.transaction.config;

import com.springJourney.transaction.dao.InMemoryDatabase;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.SimpleTransactionStatus;

import java.util.Stack;

public class MockTransactionManager implements PlatformTransactionManager {
    private final InMemoryDatabase database;

    private static class TxContext {
        final boolean isNew;
        final TransactionStatus status;

        TxContext(boolean isNew, TransactionStatus status) {
            this.isNew = isNew;
            this.status = status;
        }
    }

    private final Stack<TxContext> contexts = new Stack<>();

    public MockTransactionManager(InMemoryDatabase database) {
        this.database = database;
        System.out.println("MockTransactionManager 初始化，数据库实例：" + database);
    }

    @Override
    public TransactionStatus getTransaction(TransactionDefinition definition) {
        boolean isNewTx = definition.getPropagationBehavior() == TransactionDefinition.PROPAGATION_REQUIRES_NEW;
        TransactionContextHolder.setIsNewTransaction(isNewTx); // 设置上下文
        SimpleTransactionStatus status = new SimpleTransactionStatus(isNewTx);
        contexts.push(new TxContext(isNewTx, status));
        System.out.println("事务开始 → isNew=" + isNewTx);
        return status;
    }

    @Override
    public void commit(TransactionStatus status) {
        if (contexts.isEmpty()) return;
        TxContext ctx = contexts.pop();
        if (ctx.status != status) return;
        System.out.println("事务提交 → isNew=" + ctx.isNew);

        if (ctx.isNew) {
            database.clearNewTransactionLogs();
        }
    }

    @Override
    public void rollback(TransactionStatus status) {
        if (contexts.isEmpty()) return;
        TxContext ctx = contexts.pop();
        if (ctx.status != status) return;
        System.out.println("事务回滚 → isNew=" + ctx.isNew);
        database.rollback(ctx.isNew); // 回滚新事务或主事务
    }
}
/**
 * Propagation
 * REQUIRED - 0	如果当前有事务就加入，没有就新建（默认）
 * SUPPORTS - 1	如果有事务就加入，没有就非事务执行
 * MANDATORY - 2	必须在事务中，否则抛异常
 * REQUIRES_NEW - 3	挂起当前事务，新建一个
 * NOT_SUPPORTED - 4	总是非事务执行，挂起现有事务
 * NEVER -5	必须在非事务中，否则抛异常
 * NESTED - 6	嵌套事务，依赖保存点
 */