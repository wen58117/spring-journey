package com.springJourney.transaction.config;

import com.springJourney.transaction.dao.InMemoryDatabase;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.SimpleTransactionStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 自定义的事务管理器，配合 InMemoryDatabase 一起使用，
 * 完整模拟 Spring 事务在以下场景下的行为：
 *  - 默认传播(Propagation.REQUIRED)
 *  - 新事务传播(Propagation.REQUIRES_NEW)
 *  - 嵌套事务的隔离与提交/回滚
 */
public class MockTransactionManager implements PlatformTransactionManager {

    private final InMemoryDatabase database;

    /**
     * 每个事务的上下文，封装了：
     *  - 是不是新事务
     *  - 事务开始时的快照
     *  - 当前事务视图的记录列表
     *  - 事务状态对象
     */
    private static class TxContext {
        final boolean isNew;
        final List<String> snapshot;
        List<String> currentRecords;
        final TransactionStatus status;

        TxContext(boolean isNew, List<String> snapshot, TransactionStatus status) {
            this.isNew = isNew;
            this.snapshot = snapshot;
            // 每个事务持有自己的一份“当前视图”副本
            this.currentRecords = new ArrayList<>(snapshot);
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

        // 决定本事务的基准状态：如果是最外层，从 database 拿；否则用父上下文的 currentRecords
        List<String> baseSnapshot;
        if (contexts.isEmpty()) {
            baseSnapshot = database.snapshot();
        } else {
            baseSnapshot = contexts.peek().currentRecords;
        }

        // 为本事务创建上下文
        SimpleTransactionStatus status = new SimpleTransactionStatus(isNewTx);
        TxContext ctx = new TxContext(isNewTx, baseSnapshot, status);
        contexts.push(ctx);

        // 切换数据库到本事务视图，模拟事务隔离
        database.setRecords(ctx.currentRecords);
        System.out.println("事务开始 → isNew=" + isNewTx + "，基准快照=" + baseSnapshot);
        return status;
    }

    @Override
    public void commit(TransactionStatus status) {
        if (contexts.isEmpty()) return;
        TxContext ctx = contexts.peek();
        if (ctx.status != status) return;

        // 出栈
        contexts.pop();
        System.out.println("事务提交 → isNew=" + ctx.isNew + "，最终视图=" + ctx.currentRecords);

        if (contexts.isEmpty()) {
            // 最外层事务：提交到真实数据库
            database.setRecords(ctx.currentRecords);
        } else {
            // 嵌套或 REQUIRES_NEW：将当前事务的修改“合并”到父事务视图
            TxContext parent = contexts.peek();
            parent.currentRecords = new ArrayList<>(ctx.currentRecords);
            database.setRecords(parent.currentRecords);
        }
    }

    @Override
    public void rollback(TransactionStatus status) {
        if (contexts.isEmpty()) return;
        TxContext ctx = contexts.peek();
        if (ctx.status != status) return;

        // 出栈
        contexts.pop();
        System.out.println("事务回滚 → isNew=" + ctx.isNew + "，恢复到快照=" + (ctx.isNew ? "[父事务视图不变]" : ctx.snapshot));

        if (contexts.isEmpty()) {
            // 最外层事务回滚：恢复到自身快照
            database.restore(ctx.snapshot);
        } else {
            if (!ctx.isNew) {
                // 父事务回滚：恢复到它自己的快照，并切换数据库
                TxContext parent = contexts.peek();
                database.setRecords(parent.currentRecords);
            } else {
                // 新事务回滚：父事务视图保持不变，数据库切回父事务视图
                TxContext parent = contexts.peek();
                database.setRecords(parent.currentRecords);
            }
        }
    }
}
