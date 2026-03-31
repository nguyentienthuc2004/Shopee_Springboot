package org.thuc.shoppe.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.thuc.shoppe.repo.MemoryRepository;
import org.thuc.shoppe.service.SaveService;
import org.thuc.shoppe.service.impl.MemoryServiceImpl;
import org.thuc.shoppe.service.impl.RedisServiceImpl;

@Configuration
public class SaveServiceConfig {
    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public SaveService redisService(RedisTemplate<String, Object> redisTemplate) {
        return new RedisServiceImpl(redisTemplate);
    }
    @Bean
    @ConditionalOnMissingBean(SaveService.class)
    public SaveService memoryService(MemoryRepository memoryRepository) {
        return new MemoryServiceImpl(memoryRepository);
    }

}
