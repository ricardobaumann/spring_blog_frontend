package com.github.ricardobaumann.spring_blog_frontend.helpers;

import com.github.ricardobaumann.spring_blog_frontend.BackendConfig;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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

import java.util.List;

/**
 * Created by ricardobaumann on 22/11/16.
 */
@Component
public class BackendHelper{

    @Autowired
    private BackendConfig backendConfig;


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



}
