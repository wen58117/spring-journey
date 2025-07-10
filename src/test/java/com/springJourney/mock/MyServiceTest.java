package com.springJourney.mock;

import com.springJourney.mock.service.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MyServiceTest {

    @Mock
    private Dependency1 dependency1;

    @Mock
    private Dependency2 dependency2;

    @InjectMocks
    private AutowiredTestService autowiredTestService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this); // 初始化 Mockito 注解
    }

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