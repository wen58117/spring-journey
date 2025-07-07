package com.springJourney.designPattern.factoryPattern.factoryMethod;

// 具体支付类：微信支付
public class WeChatPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("使用微信支付：" + amount + "元");
    }
}