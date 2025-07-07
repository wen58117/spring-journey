package com.springJourney.designPattern.factoryPattern.abstractFactory;

// 具体工厂：Windows 工厂
public class WindowsUIFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public TextBox createTextBox() {
        return new WindowsTextBox();
    }
}