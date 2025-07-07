package com.springJourney.designPattern.strategyPattern;

import com.springJourney.designPattern.strategyPattern.payment.context.PaymentContext;
import com.springJourney.designPattern.strategyPattern.payment.strategy.AlipayPayment;
import com.springJourney.designPattern.strategyPattern.payment.strategy.WeChatPayment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentTest {

    @Test
    public void testPayment() {
        PaymentContext context = new PaymentContext(new AlipayPayment());
        context.processPayment(100.0);
        context.setPaymentStrategy(new WeChatPayment());
        context.processPayment(200.0);
    }
}
