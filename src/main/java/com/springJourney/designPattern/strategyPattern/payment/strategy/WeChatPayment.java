package com.springJourney.designPattern.strategyPattern.payment.strategy;

public class WeChatPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("使用微信支付: " + amount);
    }
}