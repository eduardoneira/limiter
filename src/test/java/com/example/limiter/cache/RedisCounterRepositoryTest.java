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
    private RedisCounterRepository counterRepository;

    @BeforeEach
    @AfterEach
    void deleteCounter() {
        this.counterRepository.delete(getKey());
    }

    private String getKey() {
        return "testCounter";
    }

    @Test
    void testEmptyGet() {
        assertTrue(this.counterRepository.get(getKey()).isEmpty());
    }

    @Test
    void testCreate() {
        this.counterRepository.create(getKey(), 2L);

        final Optional<Long> counter = this.counterRepository.get(getKey());
        assertTrue(counter.isPresent());
        assertEquals(2L, counter.get().longValue());
    }

    @Test
    void testDelete() {
        this.counterRepository.create(getKey(), 2L);

        this.counterRepository.delete(getKey());
        assertTrue(this.counterRepository.get(getKey()).isEmpty());
    }

    @Test
    void testIncrement() {
        this.counterRepository.create(getKey(), 3L);
        this.counterRepository.increment(getKey(), 1L);

        final Optional<Long> counter = this.counterRepository.get(getKey());

        assertTrue(counter.isPresent());
        assertEquals(4L, counter.get().longValue());
    }

    @Test
    void testDecrement() {
        this.counterRepository.create(getKey(), 4L);
        this.counterRepository.decrement(getKey());

        final Optional<Long> counter = this.counterRepository.get(getKey());

        assertTrue(counter.isPresent());
        assertEquals(3L, counter.get().longValue());

    }

}