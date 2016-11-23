package com.github.ricardobaumann.spring_blog_frontend.configuration;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by ricardobaumann on 23/11/16.
 */
public class CustomAuthentication extends UsernamePasswordAuthenticationToken {

    public String getToken() {
        return token;
    }

    private final String token;

    public CustomAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String token) {
        super(principal, credentials, authorities);
        this.token = token;
    }
}
