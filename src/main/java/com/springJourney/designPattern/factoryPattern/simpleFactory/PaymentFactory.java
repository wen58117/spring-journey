package com.springJourney.designPattern.factoryPattern.simpleFactory;

// 简单工厂类
public class PaymentFactory {
    public static Payment createPayment(String type) {
        if ("WeChat".equalsIgnoreCase(type)) {
            return new WeChatPayment();
        } else if ("Alipay".equalsIgnoreCase(type)) {
            return new AlipayPayment();
        } else {
            throw new IllegalArgumentException("不支持的支付方式：" + type);
        }
    }
}