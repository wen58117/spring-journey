package com.springJourney;

import com.springJourney.transaction.config.TransactionConfig;
import com.springJourney.transaction.service.SecondaryService;
import com.springJourney.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TransactionConfig.class)
public class SpringTransactionTest {

    @Autowired
    private TransactionService testService;

    @Test
    public void testTransactionalBehavior() {
        // 测试正常提交
        testService.executeWithTransaction("记录1");
        // 验证数据是否提交
        System.out.println("提交后，数据：" + testService.getData());
        // 测试异常回滚
        try {
            testService.executeWithTransactionAndException("记录2");
        } catch (RuntimeException e) {
            System.out.println("捕获异常：" + e.getMessage());
        }
        // 验证数据是否回滚
        System.out.println("回滚后，数据：" + testService.getData());
    }

    @Test
    public void testTransactionalBehavior1() {
        // 测试异常回滚,新事物不回滚
        try {
            testService.executeWithTransactionAndException2("记录2");
        } catch (RuntimeException e) {
            System.out.println("捕获异常：" + e.getMessage());
        }
        // 验证数据是否回滚
        System.out.println("回滚后，数据：" + testService.getData());
    }

}
