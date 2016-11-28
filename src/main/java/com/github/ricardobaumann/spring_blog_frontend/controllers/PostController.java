package com.github.ricardobaumann.spring_blog_frontend.controllers;

import com.github.ricardobaumann.spring_blog_frontend.PostService;
import com.github.ricardobaumann.spring_blog_frontend.configuration.CustomAuthentication;
import com.github.ricardobaumann.spring_blog_frontend.helpers.Converter;
import com.github.ricardobaumann.spring_blog_frontend.models.Post;
import com.github.ricardobaumann.spring_blog_frontend.pageforms.PostsForm;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by ricardobaumann on 23/11/16.
 */
@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private Converter converter;

    @PostMapping("/posts")
    public String post(@Valid PostsForm postsForm, BindingResult bindingResult, CustomAuthentication customAuthentication) throws Exception {



        if (!bindingResult.hasErrors()) {
            Post post = converter.convert(postsForm,Post.class);
            post = postService.createPost(post,customAuthentication);
            postsForm.setId(post.getId());
        } else {
            return "posts";
        }

        //System.out.println(customAuthentication.getToken());


        return "redirect:/posts";
    }

    @GetMapping("/posts/delete")
    public String delete(HttpServletRequest httpServletRequest, CustomAuthentication customAuthentication) {
        postService.delete(Long.parseLong(httpServletRequest.getParameter("id")),customAuthentication);
        return "redirect:/posts";
    }

    @GetMapping("/posts")
    public String get(PostsForm postsForm, CustomAuthentication customAuthentication) {
        postsForm.setPosts(postService.getPage(customAuthentication));
        return "posts";
    }

}
