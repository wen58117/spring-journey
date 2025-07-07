package com.springJourney.designPattern.factoryPattern.abstractFactory;

// 具体产品：Windows 文本框
public class WindowsTextBox implements TextBox {
    @Override
    public void input() {
        System.out.println("输入 Windows 风格文本框");
    }
}