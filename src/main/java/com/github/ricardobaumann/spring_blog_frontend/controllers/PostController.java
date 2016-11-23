package com.github.ricardobaumann.spring_blog_frontend.controllers;

import com.github.ricardobaumann.spring_blog_frontend.configuration.CustomAuthentication;
import com.github.ricardobaumann.spring_blog_frontend.pageforms.PostsForm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by ricardobaumann on 23/11/16.
 */
@Controller
public class PostController {

    @PostMapping("/posts")
    public String post(@Valid PostsForm postsForm, BindingResult bindingResult, CustomAuthentication customAuthentication) {

        if (!bindingResult.hasErrors()) {

        }

        System.out.println(customAuthentication.getToken());

        return "posts";
    }

    @GetMapping("/posts")
    public String get(PostsForm postsForm) {

        return "posts";
    }

}
