package com.springJourney.mock;

import com.springJourney.mock.service.MyService;
import com.springJourney.mock.service.ThirdPartyService;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MyServiceTest {

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
}