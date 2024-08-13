package com.example.limiter.rule;

import com.example.limiter.executor.ScheduledExecutorMock;
import com.example.limiter.resource.tokenbucket.TokenBucketConfiguration;
import com.example.limiter.resource.tokenbucket.TokenBucketFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IPRuleTest {

    @Mock
    private HttpServletRequest request1;

    @Mock
    private HttpServletRequest request2;

    @Test
    void testAllow() {
        final ScheduledExecutorMock executor = new ScheduledExecutorMock();
        final TokenBucketFactory factory = new TokenBucketFactory(
                new TokenBucketConfiguration(1, 1, 1),
                (_, _) -> executor);

        final IPRule ipFilter = new IPRule(factory);

        when(this.request1.getRequestURI()).thenReturn("/test");
        when(this.request1.getRemoteAddr()).thenReturn("10.0.0.1");

        when(this.request2.getRequestURI()).thenReturn("/test");
        when(this.request2.getRemoteAddr()).thenReturn("10.0.0.2");

        final Request request1 = new Request(this.request1);
        final Request request2 = new Request(this.request2);

        assertTrue(ipFilter.allow(request1));
        assertFalse(ipFilter.allow(request1));
        assertTrue(ipFilter.allow(request2));

        executor.tick();

        assertTrue(ipFilter.allow(request1));
        assertTrue(ipFilter.allow(request2));
    }

}