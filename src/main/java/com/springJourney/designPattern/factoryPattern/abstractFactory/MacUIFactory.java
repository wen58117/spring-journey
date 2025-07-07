package com.springJourney.designPattern.factoryPattern.abstractFactory;

// 具体工厂：Mac 工厂
public class MacUIFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public TextBox createTextBox() {
        return new MacTextBox();
    }
}