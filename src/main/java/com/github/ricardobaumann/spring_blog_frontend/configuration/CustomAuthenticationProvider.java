package com.github.ricardobaumann.spring_blog_frontend.configuration;

import com.github.ricardobaumann.spring_blog_frontend.helpers.BackendHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by ricardobaumann on 23/11/16.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private BackendHelper backendHelper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        OAuth2AccessToken token = shouldAuthenticateAgainstThirdPartySystem(name, password);

        if (token!=null) {
            // use the credentials and authenticate against the third-party system
            return new CustomAuthentication(name,password,new ArrayList<>(),token.getValue());
        } else {
            return null;
        }
    }

    private OAuth2AccessToken shouldAuthenticateAgainstThirdPartySystem(String name, String password) {
        return backendHelper.getToken(name,password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
