package com.reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class FluxConfig {

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .defaultHeader("Accept","application/vnd.github+json")
                .build();
    }
}
