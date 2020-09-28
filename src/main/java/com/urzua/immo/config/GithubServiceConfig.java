package com.urzua.immo.config;

import com.urzua.immo.config.properties.GithubServiceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Slf4j
@Configuration
public class GithubServiceConfig {

    private final GithubServiceProperties properties;

    public GithubServiceConfig(final GithubServiceProperties properties) {
        this.properties = properties;
    }


    @Bean("restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(2))
                .setReadTimeout(Duration.ofSeconds(2))
                .build();
    }
}
