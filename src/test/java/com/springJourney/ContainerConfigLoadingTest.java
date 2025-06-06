package com.springJourney;

import com.springJourney.config.ManualConfig;
import com.springJourney.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
//@ContextConfiguration(classes = {com.springJourney.config.ImportConfig.class})
class ContainerConfigLoadingTest {

    @Autowired
    private ApplicationContext context;

    // 测试通过 @ComponentScan 加载 ScanConfig
    @Test
    void testScanConfig() {
        MyService scanService = context.getBean("scanService", MyService.class);
        assertNotNull(scanService, "ScanService 应不为空");
        assertEquals("ScanConfig 成功加载！", scanService.sayHello());
    }

    // 测试通过 META-INF/spring.factories 加载 AutoConfig
    @Test
    void testAutoConfig() {
        MyService autoService = context.getBean("autoService", MyService.class);
        assertNotNull(autoService, "AutoService 应不为空");
        assertEquals("AutoConfig 成功加载！", autoService.sayHello());
    }

    // 测试通过 @Import 加载 ImportConfig -- 测试时开启类注解@ContextConfiguration
    @Test
    void testImportConfig() {
        MyService importService = context.getBean("importService", MyService.class);
        assertNotNull(importService, "ImportService 应不为空");
        assertEquals("ImportConfig 成功加载！", importService.sayHello());
    }

    // 测试通过 AnnotationConfigApplicationContext 手动注册 ManualConfig
    @Test
    void testManualConfig() {
        AnnotationConfigApplicationContext manualContext = new AnnotationConfigApplicationContext();
        manualContext.register(ManualConfig.class);
        manualContext.refresh();

        MyService manualService = manualContext.getBean("manualService", MyService.class);
        assertNotNull(manualService, "ManualService 应不为空");
        assertEquals("ManualConfig 成功加载！", manualService.sayHello());

        manualContext.close();
    }

    // 测试通过 XML 配置加载 XmlConfig
    @Test
    void testXmlConfig() {
        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyService xmlService = xmlContext.getBean("xmlService", MyService.class);
        assertNotNull(xmlService, "XmlService 应不为空");
        assertEquals("XmlConfig 成功加载！", xmlService.sayHello());

        xmlContext.close();
    }
}