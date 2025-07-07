package com.springJourney.designPattern.factoryPattern.abstractFactory;

// 抽象工厂接口
public interface UIFactory {
    Button createButton();
    TextBox createTextBox();
}