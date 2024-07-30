package com.example.limiter.restservice;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
class RateLimiterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void validate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/validateRequest"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Valid")));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/validateRequest"))
                .andExpect(MockMvcResultMatchers.status().isTooManyRequests());
    }
}