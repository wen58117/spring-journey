package com.springJourney.lazy;

import com.springJourney.lazy.config.LazyAppConfig;
import com.springJourney.lazy.service.LazySingletonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = LazyAppConfig.class)
public class LazyLoadingTest {

    @Test
    public void testLazyLoading(@Autowired ApplicationContext applicationContext) {
        System.out.println("[" + System.currentTimeMillis() + "] Spring 容器已启动（有 @Lazy 注解）");

        // 此时 LazySingletonService 还未被创建 此处断点，启动时不会调用LazySingletonService的构造方法。等执行到getBean时才会调用LazySingletonService的构造方法
        LazySingletonService service1 = applicationContext.getBean(LazySingletonService.class);
        assertNotNull(service1, "LazySingletonService 实例不应为 null");

        // 调用方法
        service1.doSomething();

        // 再次获取 Bean，验证是否为单例
        LazySingletonService service2 = applicationContext.getBean(LazySingletonService.class);
        assertNotNull(service2, "LazySingletonService 实例不应为 null");
        // 验证是否为同一个实例
        assert service1 == service2;
    }
}
