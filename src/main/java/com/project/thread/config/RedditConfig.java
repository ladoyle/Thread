package com.project.thread.config;

import com.project.thread.services.RedditService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.project.thread")
public class RedditConfig {

    @Bean
    public RedditService redditService() {
        return RedditService.builder().build();
    }
}
