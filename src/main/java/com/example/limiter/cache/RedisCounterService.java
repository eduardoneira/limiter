package com.example.limiter.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedisCounterService {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisCounterService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void create(String key, long value) {
        this.redisTemplate.opsForValue().setIfAbsent(key, String.valueOf(value));
    }

    public void delete(String key) {
        this.redisTemplate.delete(key);
    }

    public void increment(String key, long delta) {
        this.redisTemplate.opsForValue().increment(key, delta);
    }

    public void decrement(String key) {
        this.redisTemplate.opsForValue().decrement(key);
    }

    public Optional<Long> get(String key) {
        final String value = this.redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(value).map(Long::parseLong);
    }

}
