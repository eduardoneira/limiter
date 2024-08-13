package com.example.limiter.resource.tokenbucket;

import com.example.limiter.cache.RedisConfig;
import com.example.limiter.cache.RedisCounterRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {RedisConfig.class, RedisCounterRepository.class})
@ActiveProfiles("test")
class RemoteBucketTest {

    @Autowired
    private RedisCounterRepository counterRepository;

    @BeforeEach
    @AfterEach
    void deleteCounter() {
        this.counterRepository.delete(getId());
    }

    private String getId() {
        return "bucketTest";
    }

    @Test
    void useTest() {
        final RemoteBucket remoteBucket = new RemoteBucket(this.counterRepository, 1, getId());

        assertTrue(remoteBucket.use());
        assertFalse(remoteBucket.use());
    }

}