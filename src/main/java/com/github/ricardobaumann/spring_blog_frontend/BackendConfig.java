package com.github.ricardobaumann.spring_blog_frontend;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by ricardobaumann on 22/11/16.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "backend")
@Data
public class BackendConfig {

    private String url;

    private String clientId;

    private String clientSecret;

    private String grantType;

    private List<String> scopes;

}
