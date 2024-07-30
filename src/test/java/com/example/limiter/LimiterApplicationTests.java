package com.example.limiter;

import com.example.limiter.restservice.RateLimiterController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LimiterApplicationTests {

	@Autowired
	private RateLimiterController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
