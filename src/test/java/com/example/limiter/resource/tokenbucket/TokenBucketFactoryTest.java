package com.example.limiter.resource.tokenbucket;

import com.example.limiter.cache.RedisConfig;
import com.example.limiter.cache.RedisCounterRepository;
import com.example.limiter.executor.ScheduledExecutorMock;
import com.example.limiter.resource.Resource;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {RedisConfig.class, RedisCounterRepository.class})
@ActiveProfiles("test")
class TokenBucketFactoryTest {

    @Autowired
    private RedisCounterRepository redisCounterRepository;

    @BeforeEach
    @AfterEach
    public void deleteCounter() {
        this.redisCounterRepository.delete(getId());
    }


    @Test
    void testLocalCreate() {
        final ScheduledExecutorMock executor = new ScheduledExecutorMock();
        testBucket(createFactory(executor, false), executor);
    }

    @Test
    void testRemoteCreate() {
        final ScheduledExecutorMock executor = new ScheduledExecutorMock();
        testBucket(createFactory(executor, true), executor);
    }

    private TokenBucketFactory createFactory(ScheduledExecutorMock executor, boolean useRemote) {
        return new TokenBucketFactory(
                new TokenBucketConfiguration(1, 1, 1, useRemote),
                (_, _) -> executor,
                this.redisCounterRepository);
    }

    private void testBucket(TokenBucketFactory factory, ScheduledExecutorMock executor) {
        final Resource resource = factory.create(getId());

        assertTrue(resource.use());
        assertFalse(resource.use());

        executor.tick();

        assertTrue(resource.use());
    }

    private static String getId() {
        return "test";
    }
}