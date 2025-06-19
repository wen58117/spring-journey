package com.springJourney;

import com.springJourney.template.service.TaskTest;
import com.springJourney.template.service.TaskTest1;
import com.springJourney.template.service.TemplateDemo;
import org.junit.jupiter.api.Test;

public class TemplateTest {

    @Test
    void AnonymousInnerTest() {
        String o = new TemplateDemo().executeTask(new TaskTest() {
            @Override
            public String execute() {
                return "业务代码被执行";
            }
        }).toString();
        System.out.println(o);
    }


    @Test
    void AnonymousInnerTest1() {
        String o = new TemplateDemo().executeTask1(new TaskTest1<String,String>() {
            @Override
            public String execute(String input) {
                if("输入".equals(input)){
                    return "输出";
                }
                return "未知输入";
            }
        },"输入");
        System.out.println(o);
    }

}
