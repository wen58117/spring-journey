package com.springJourney.beanLifecycle;

import com.springJourney.beanLifecycle.bean.MyBean;
import com.springJourney.beanLifecycle.config.MyConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanLifecycleTest {

    @Test
    public void testBeanLifecycle() {
        System.out.println("🟢 启动容器");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        MyBean bean = context.getBean(MyBean.class);
        System.out.println("🟢 Bean 获取完成：" + bean);

        System.out.println("🔴 关闭容器");
        context.close();
    }
}

/**
 * 🟢 启动容器
 * 1️⃣ MyBean 构造方法调用                            ← 实例化
 * 🔁 BeanPostProcessor.beforeInitialization 被调用  ← 初始化前处理器
 * 2️⃣ @PostConstruct 调用                           ← 初始化步骤1
 * 3️⃣ InitializingBean.afterPropertiesSet 调用       ← 初始化步骤2
 * 4️⃣ 自定义初始化方法调用                          ← 初始化步骤3
 * 🔁 BeanPostProcessor.afterInitialization 被调用   ← 初始化后处理器
 * 🟢 Bean 获取完成：com.example.MyBean@xxx
 * 🔴 关闭容器
 * 7️⃣ @PreDestroy 调用                              ← 销毁步骤1
 * 8️⃣ DisposableBean.destroy 调用                   ← 销毁步骤2
 * 9️⃣ 自定义销毁方法调用                            ← 销毁步骤3
 */