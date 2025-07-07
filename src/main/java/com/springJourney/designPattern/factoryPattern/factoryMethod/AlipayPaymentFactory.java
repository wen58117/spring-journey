package com.springJourney.designPattern.factoryPattern.factoryMethod;

// 具体工厂：支付宝支付工厂
public class AlipayPaymentFactory implements PaymentFactory {
    @Override
    public Payment createPayment() {
        return new AlipayPayment();
    }
}