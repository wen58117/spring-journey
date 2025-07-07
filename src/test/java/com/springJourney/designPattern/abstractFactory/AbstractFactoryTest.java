package com.springJourney.designPattern.abstractFactory;

import com.springJourney.designPattern.factoryPattern.abstractFactory.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = {"com.springJourney.designPattern.factoryPattern.abstractFactory"})
public class AbstractFactoryTest {

    @Test
    public void testAbstractFactory() {
        // Windows 风格 UI
        UIFactory windowsFactory = new WindowsUIFactory();
        Button windowsButton = windowsFactory.createButton();
        TextBox windowsTextBox = windowsFactory.createTextBox();
        windowsButton.render(); // 输出：渲染 Windows 风格按钮
        windowsTextBox.input(); // 输出：输入 Windows 风格文本框

        // Mac 风格 UIusa
        UIFactory macFactory = new MacUIFactory();
        Button macButton = macFactory.createButton();
        TextBox macTextBox = macFactory.createTextBox();
        macButton.render(); // 输出：渲染 Mac 风格按钮
        macTextBox.input(); // 输出：输入 Mac 风格文本框
    }
}
