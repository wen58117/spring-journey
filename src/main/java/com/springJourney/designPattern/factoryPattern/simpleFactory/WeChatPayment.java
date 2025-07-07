package com.springJourney.designPattern.factoryPattern.simpleFactory;

// 具体支付类：微信支付
class WeChatPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("使用微信支付：" + amount + "元");
    }
}