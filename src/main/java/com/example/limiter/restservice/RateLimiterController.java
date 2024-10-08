package com.example.limiter.restservice;

import com.example.limiter.cache.RedisCounterRepository;
import com.example.limiter.executor.BackgroundExecutor;
import com.example.limiter.resource.tokenbucket.TokenBucketConfiguration;
import com.example.limiter.resource.tokenbucket.TokenBucketFactory;
import com.example.limiter.rule.IPRule;
import com.example.limiter.rule.Request;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RateLimiterController {

    private final IPRule ipRule;

    public RateLimiterController(TokenBucketConfiguration tokenBucketConfiguration, RedisCounterRepository redisCounterRepository) {
        this.ipRule = new IPRule(new TokenBucketFactory(
                tokenBucketConfiguration,
                new BackgroundExecutor.Factory(),
                redisCounterRepository));
    }

    @GetMapping("/validateRequest")
    public String validate(HttpServletRequest request) {
        if (ipRule.allow(new Request(request)))
            return "Valid";

        throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS);
    }


}
