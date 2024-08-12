package com.example.limiter.cache;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {RedisConfig.class, RedisCounterRepository.class})
@ActiveProfiles("test")
class RedisCounterRepositoryTest {

    @Autowired
    private RedisCounterRepository counterService;

    @BeforeEach
    @AfterEach
    void deleteCounter() {
        this.counterService.delete(getKey());
    }

    private String getKey() {
        return "testCounter";
    }

    @Test
    void testEmptyGet() {
        assertTrue(this.counterService.get(getKey()).isEmpty());
    }

    @Test
    void testCreate() {
        this.counterService.create(getKey(), 2L);

        final Optional<Long> counter = this.counterService.get(getKey());
        assertTrue(counter.isPresent());
        assertEquals(2L, counter.get().longValue());
    }

    @Test
    void testDelete() {
        this.counterService.create(getKey(), 2L);

        this.counterService.delete(getKey());
        assertTrue(this.counterService.get(getKey()).isEmpty());
    }

    @Test
    void testIncrement() {
        this.counterService.create(getKey(), 3L);
        this.counterService.increment(getKey(), 1L);

        final Optional<Long> counter = this.counterService.get(getKey());

        assertTrue(counter.isPresent());
        assertEquals(4L, counter.get().longValue());
    }

    @Test
    void testDecrement() {
        this.counterService.create(getKey(), 4L);
        this.counterService.decrement(getKey());

        final Optional<Long> counter = this.counterService.get(getKey());

        assertTrue(counter.isPresent());
        assertEquals(3L, counter.get().longValue());

    }

}