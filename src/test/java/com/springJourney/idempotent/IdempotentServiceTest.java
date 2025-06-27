package com.springJourney.idempotent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springJourney.common.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@EnableAspectJAutoProxy // 启用AspectJ代理
@EnableAsync // 启用异步支持
@AutoConfigureMockMvc
@ComponentScan(basePackages = {"com.springJourney.idempotent", "com.springJourney.common"})
public class IdempotentServiceTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testIdempotent_Success() throws Exception {
        Map<String, String> input = new HashMap<>();
        input.put("idempotentId", "123");
        input.put("idempotent", "testValue");

        mockMvc.perform(MockMvcRequestBuilders.post("/idempotent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("成功"))
                .andExpect(jsonPath("$.data").value("idempotent调用成功123"));
    }

    @Test
    void testIdempotent_Duplicate() throws Exception {
        Map<String, String> input = new HashMap<>();
        input.put("idempotentId", "123");
        input.put("idempotent", "testValue");

        // 第一次请求
        mockMvc.perform(MockMvcRequestBuilders.post("/idempotent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());

        // 第二次请求，模拟重复提交
//        mockMvc.perform(MockMvcRequestBuilders.post("/idempotent")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(input)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value("400"))
//                .andExpect(jsonPath("$.message").value("请勿重复提交"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/idempotent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andReturn();

        // 设置响应的字符编码为 UTF-8
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        // 获取响应内容
        String responseContent = mvcResult.getResponse().getContentAsString();
        // 反序列化为 Result 对象
        Result<String> result = objectMapper.readValue(responseContent, objectMapper.getTypeFactory().constructParametricType(Result.class, String.class));

        System.out.println(result);
    }

    @Test
    void testIdempotent_DuplicateAndRelease() throws Exception {
        Map<String, String> input = new HashMap<>();
        input.put("idempotentId", "123");
        input.put("idempotent", "testValue");

        // 第一次请求
        mockMvc.perform(MockMvcRequestBuilders.post("/idempotent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());

        // 第二次请求，模拟重复提交
        mockMvc.perform(MockMvcRequestBuilders.post("/idempotent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("请勿重复提交"));

        // 等待幂等键过期，接口设置过期时间是 4 秒，这里等待 5 秒确保过期
        Thread.sleep(5000);

        // 第三次请求，验证幂等键过期后是否能正常处理
        mockMvc.perform(MockMvcRequestBuilders.post("/idempotent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("成功"))
                .andExpect(jsonPath("$.data").value("idempotent调用成功123"));
    }
}