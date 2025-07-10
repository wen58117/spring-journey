package com.springJourney.mock;

import com.springJourney.mock.config.MockConfig;
import com.springJourney.mock.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockConfig.class)
public class MyServiceTest {

    @MockBean
    private Dependency1 dependency1;

    @MockBean
    private Dependency2 dependency2;

    @Autowired
    private AutowiredTestService autowiredTestService;

    @Test
    public void testFetchData() throws IOException {
        // 创建 ThirdPartyService 的 Mock 对象
        ThirdPartyService mockThirdPartyService = Mockito.mock(ThirdPartyService.class);
        // 模拟 getData 方法的返回值
        String mockResponse = "Mocked Data from Third Party API";
        Mockito.when(mockThirdPartyService.getData()).thenReturn(mockResponse);

        // 创建 MyService 实例并注入 Mock 对象
        MyService myService = new MyService(mockThirdPartyService);

        // 调用 MyService 的 fetchData 方法
        String result = myService.fetchData();

        // 验证 ThirdPartyService 的 getData 方法被调用
        Mockito.verify(mockThirdPartyService, Mockito.times(1)).getData();
        // 验证返回结果
        assertEquals(mockResponse, result);
    }

    @Test
    public void testAutowiredMock() {
        Mockito.when(dependency1.method1()).thenReturn("mock1");
        when(dependency2.method2()).thenReturn("mock2");
        String result = autowiredTestService.complexMethod();
        assertEquals("mock1mock2", result);
    }
}