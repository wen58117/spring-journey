package com.springJourney.idempotent.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MockRedisTemplate {
    private final Map<String, String> dataStore = new HashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * 设置键值对并指定过期时间
     * @param key 键
     * @param value 值
     * @param timeout 过期时间
     * @param unit 时间单位
     */
    public void opsForValueSet(String key, String value, long timeout, TimeUnit unit) {
        dataStore.put(key, value);
        // 定时任务，在指定时间后删除键值对
        scheduler.schedule(() -> dataStore.remove(key), timeout, unit);
    }

    /**
     * 检查键是否存在
     * @param key 键
     * @return 存在返回 true，不存在返回 false
     */
    public boolean hasKey(String key) {
        return dataStore.containsKey(key);
    }

    /**
     * 删除键值对
     * @param key 键
     * @return 删除成功返回 true，失败返回 false
     */
    public boolean delete(String key) {
        return dataStore.remove(key) != null;
    }

    /**
     * 关闭调度器
     */
    public void shutdown() {
        scheduler.shutdown();
    }
}