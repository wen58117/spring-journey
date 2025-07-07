package com.springJourney.designPattern.factoryPattern.factoryMethod;

// 具体工厂：微信支付工厂
public class WeChatPaymentFactory implements PaymentFactory {
    @Override
    public Payment createPayment() {
        return new WeChatPayment();
    }
}