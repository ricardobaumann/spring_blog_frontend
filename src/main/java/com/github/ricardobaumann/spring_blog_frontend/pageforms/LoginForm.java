package com.github.ricardobaumann.spring_blog_frontend.pageforms;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by ricardobaumann on 22/11/16.
 */
@Data
public class LoginForm {

    @NotNull
    private String username;

    @NotNull
    private String password;

}
