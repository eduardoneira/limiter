package com.example.limiter;

import com.example.limiter.resource.tokenbucket.TokenBucketConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TokenBucketConfiguration.class)
public class LimiterApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimiterApplication.class, args);
	}

}
