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
import org.springframework.stereotype.Component;
/**
 * Created by ricardobaumann on 22/11/16.
 */
//@Component
//@Configuration
//@EnableOAuth2Client
public class BackendHelper extends WebSecurityConfigurerAdapter{

    @Autowired
    private BackendConfig backendConfig;

    /*
    public OAuth2AccessToken getToken(String username, String password) {

        return getRestTemplate(username,password).getAccessToken();
    }
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();


    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    /*
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

        resource.setClientAuthenticationScheme(AuthenticationScheme.none);

        return resource;
    }
     */

    private String getTokenURI() {
        return String.format("%s/oauth/token",backendConfig.getUrl());
    }


    /*
    public OAuth2RestOperations getRestTemplate(String userName, String password) {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();

        return new OAuth2RestTemplate(resource(userName, password), new DefaultOAuth2ClientContext(atr));
    }

     */

}
