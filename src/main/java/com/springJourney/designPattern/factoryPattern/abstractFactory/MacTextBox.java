package com.springJourney.designPattern.factoryPattern.abstractFactory;

// 具体产品：Mac 文本框
public class MacTextBox implements TextBox {
    @Override
    public void input() {
        System.out.println("输入 Mac 风格文本框");
    }
}