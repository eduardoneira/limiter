package com.example.limiter.rule;

import com.example.limiter.executor.ScheduledExecutorMock;
import com.example.limiter.resource.tokenbucket.TokenBucketFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPRuleTest {

    @Test
    void testAllow() {
        final ScheduledExecutorMock executor = new ScheduledExecutorMock();
        final TokenBucketFactory factory = new TokenBucketFactory(1, 1, 1, (_, _) -> executor);

        final IPRule ipFilter = new IPRule(factory);

        final String ip1 = "10.0.0.1";
        final String ip2 = "10.0.0.2";

        assertTrue(ipFilter.allow(ip1));
        assertTrue(ipFilter.allow(ip2));

        assertFalse(ipFilter.allow(ip1));
        assertFalse(ipFilter.allow(ip2));

        executor.tick();

        assertTrue(ipFilter.allow(ip1));
        assertTrue(ipFilter.allow(ip2));
    }

}