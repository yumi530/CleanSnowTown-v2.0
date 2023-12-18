package com.project.cleansnowtown.config.redis;

import java.time.Duration;

public interface RedisService {
    void setValues(String key, String data);

    void setValues(String key, String data, Duration duration);

    String getValue(String key);

    void deleteValues(String key);

    boolean hasKey(String key);
}
