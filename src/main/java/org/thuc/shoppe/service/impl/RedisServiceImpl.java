package org.thuc.shoppe.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thuc.shoppe.service.SaveService;


@RequiredArgsConstructor
public class RedisServiceImpl implements SaveService {
    private final RedisTemplate<String,Object> redisTemplate;

    @Override
    public void saveValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }
}
