package com.github.ricardobaumann.spring_blog_frontend.helpers;

import com.github.ricardobaumann.spring_blog_frontend.BackendConfig;
import com.github.ricardobaumann.spring_blog_frontend.models.Post;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ricardobaumann on 22/11/16.
 */
@Component
@Configuration
public class BackendHelper{

    @Autowired
    private BackendConfig backendConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    public OAuth2AccessToken getToken(String username, String password) {

        return getRestTemplate(username,password).getAccessToken();
    }




    protected OAuth2ProtectedResourceDetails resource(String user, String password) {

        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

        List scopes = backendConfig.getScopes();
        resource.setAccessTokenUri(getTokenURI());
        resource.setClientId(backendConfig.getClientId());
        resource.setClientSecret(backendConfig.getClientSecret());
        resource.setGrantType(backendConfig.getGrantType());
        resource.setScope(scopes);

        resource.setUsername(user);
        resource.setPassword(password);

        return resource;
    }


    private String getTokenURI() {
        return String.format("%s/oauth/token",backendConfig.getUrl());
    }



    public OAuth2RestOperations getRestTemplate(String userName, String password) {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();

        return new OAuth2RestTemplate(resource(userName, password), new DefaultOAuth2ClientContext(atr));
    }

    public <T> T post(Object entity, String path, String token, Class<T> responseType) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        headers.set("Authorization", String.format("Bearer %s",token));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        return restTemplate.postForObject(getURL(path),request,responseType);
    }

    @SneakyThrows
    private URI getURL(String path) {
        return new URI(String.format("%s/%s",backendConfig.getUrl(),path));
    }


}
