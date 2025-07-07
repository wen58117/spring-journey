package com.springJourney.designPattern.factoryPattern.factoryMethod;

// 具体支付类：支付宝支付
public class AlipayPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("使用支付宝支付：" + amount + "元");
    }
}