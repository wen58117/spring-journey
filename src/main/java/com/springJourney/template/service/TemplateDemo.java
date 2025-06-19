package com.springJourney.template.service;

public class TemplateDemo {

    public <T> T executeTask(TaskTest<T> task) {
        // 调用前日志
        System.out.println("业务代码调用前: " + java.time.LocalDateTime.now());

        // 执行任务
        T result = task.execute();

        // 调用后日志
        System.out.println("业务代码调用后: " + java.time.LocalDateTime.now() + "业务代码执行返回:" + result);

        return result;
    }

    public <I,O> O executeTask1(TaskTest1<I,O> task, I i) {
        // 调用前日志
        System.out.println("业务代码调用前: " + java.time.LocalDateTime.now());

        // 执行任务
        O result = task.execute(i);

        // 调用后日志
        System.out.println("业务代码调用后: " + java.time.LocalDateTime.now() + " 业务代码执行返回:" + result);

        return result;
    }
}