package com.example.limiter.cache;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RedisCounterRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final RedisScript<Boolean> decrementUpToMinScript;

    public RedisCounterRepository(RedisTemplate<String, String> redisTemplate,
                                  RedisScript<Boolean> decrementUpToMinScript) {
        this.redisTemplate = redisTemplate;
        this.decrementUpToMinScript = decrementUpToMinScript;
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

    public boolean decrementUpToMin(String key, long minValue) {
        final Boolean decrementResult = this.redisTemplate.execute(
                this.decrementUpToMinScript,
                List.of(key, String.valueOf(minValue)));

        return Boolean.TRUE.equals(decrementResult);
    }

    public Optional<Long> get(String key) {
        final String value = this.redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(value).map(Long::parseLong);
    }

}
