package com.springJourney.designPattern.strategyPattern.payment.strategy;

public class AlipayPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("使用支付宝支付: " + amount);
    }
}