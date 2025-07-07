package com.springJourney.designPattern.factoryMethod;




import com.springJourney.designPattern.factoryPattern.factoryMethod.AlipayPaymentFactory;
import com.springJourney.designPattern.factoryPattern.factoryMethod.Payment;
import com.springJourney.designPattern.factoryPattern.factoryMethod.PaymentFactory;
import com.springJourney.designPattern.factoryPattern.factoryMethod.WeChatPaymentFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = {"com.springJourney.designPattern.factoryPattern.factoryMethod"})
public class FactoryMethodTest {

    @Test
    public void testFactoryMethod() {
        PaymentFactory weChatFactory = new WeChatPaymentFactory();
        Payment weChatPayment = weChatFactory.createPayment();
        weChatPayment.pay(100.0); // 输出：使用微信支付：100.0元

        PaymentFactory alipayFactory = new AlipayPaymentFactory();
        Payment alipayPayment = alipayFactory.createPayment();
        alipayPayment.pay(200.0); // 输出：使用支付宝支付：200.0元
    }
}
