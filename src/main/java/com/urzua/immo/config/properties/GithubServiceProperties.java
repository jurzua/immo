package com.urzua.immo.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "github-service")
public class GithubServiceProperties {

    private Integer connectionPoolSize;

    private Integer readTimeoutSeconds;
}
