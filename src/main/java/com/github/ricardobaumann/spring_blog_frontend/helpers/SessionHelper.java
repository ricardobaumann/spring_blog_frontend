package com.github.ricardobaumann.spring_blog_frontend.helpers;

import com.github.ricardobaumann.spring_blog_frontend.dtos.UserDTO;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by ricardobaumann on 22/11/16.
 */
@Component
@Scope(scopeName = "singleton")
@Data
public class SessionHelper {

    private UserDTO userDTO;

}
