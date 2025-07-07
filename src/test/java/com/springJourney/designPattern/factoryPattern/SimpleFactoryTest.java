package com.springJourney.designPattern.factoryPattern;

import com.springJourney.designPattern.factoryPattern.simpleFactory.Payment;
import com.springJourney.designPattern.factoryPattern.simpleFactory.PaymentFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimpleFactoryTest {

    @Test
    public void testSimpleFactory() {
        Payment payment1 = PaymentFactory.createPayment("WeChat");
        payment1.pay(100.0); // 输出：使用微信支付：100.0元

        Payment payment2 = PaymentFactory.createPayment("Alipay");
        payment2.pay(200.0); // 输出：使用支付宝支付：200.0元
    }
}
