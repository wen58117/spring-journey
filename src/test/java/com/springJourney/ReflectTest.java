package com.springJourney;

import com.springJourney.reflect.service.cjLib.CjLibHello;
import com.springJourney.reflect.service.cjLib.MyInterceptor;
import com.springJourney.reflect.service.jdk.JdkHello;
import com.springJourney.reflect.service.jdk.JdkHelloImpl;
import com.springJourney.reflect.service.jdk.MyHandler;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

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
        JdkHello target = new JdkHelloImpl();
        JdkHello proxy = (JdkHello) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//获取目标类的类加载器
                target.getClass().getInterfaces(),//获取目标对象（HelloImpl）实现的所有接口
                new MyHandler(target)//自定义处理器
        );
        proxy.sayHello();
    }

    /**
     * 含义：CGLIB（Code Generation Library）是一种基于字节码操作的第三方库，通过生成目标类的子类来实现动态代理，无需目标类实现接口。
     * 核心组件：
     * net.sf.cglib.proxy.Enhancer：用于生成代理类。
     * MethodInterceptor：定义拦截逻辑，处理方法调用。
     * 工作原理：CGLIB 动态生成目标类的子类，重写目标方法，调用委托给 MethodInterceptor 的 intercept 方法。
     * 要求：目标类不能是 final 类，方法不能是 final 或 private（因为需要继承和重写）。
     */
    @Test
    public void cjLibProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CjLibHello.class);
        enhancer.setCallback(new MyInterceptor(new CjLibHello()));
        CjLibHello proxy = (CjLibHello) enhancer.create();
        proxy.sayHello();
    }
}
