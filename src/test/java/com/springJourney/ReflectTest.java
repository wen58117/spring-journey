package com.springJourney;

import com.springJourney.reflect.service.Hello;
import com.springJourney.reflect.service.HelloImpl;
import com.springJourney.reflect.service.MyHandler;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

public class ReflectTest {

    /**
     * JDK 动态代理的核心就是基于接口实现的，它允许在接口方法的调用前后插入自定义操作（即 AOP 中的切面逻辑）
     * JDK 动态代理的核心含义
     * 基于接口：JDK 动态代理要求目标类实现至少一个接口，代理对象通过 java.lang.reflect.Proxy 动态生成，实现相同的接口。
     * 自定义操作：通过实现 InvocationHandler 接口的 invoke 方法，可以在目标接口方法的调用前、调用后或异常处理时插入自定义逻辑，比如日志记录、权限检查、事务管理等。
     * 工作机制：
     * 代理对象接管对接口方法的调用。
     * 每次调用接口方法时，实际会调用 InvocationHandler 的 invoke 方法，在其中可以定义前置（before）、后置（after）或异常处理逻辑。
     */
    @Test
    public void jdkProxy() {
        Hello target = new HelloImpl();
        Hello proxy = (Hello) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//获取目标类的类加载器
                target.getClass().getInterfaces(),//获取目标对象（HelloImpl）实现的所有接口
                new MyHandler(target)//自定义处理器
        );
        proxy.sayHello();
    }
}
