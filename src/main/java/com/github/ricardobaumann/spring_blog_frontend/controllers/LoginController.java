package com.github.ricardobaumann.spring_blog_frontend.controllers;

import com.github.ricardobaumann.spring_blog_frontend.dtos.UserDTO;
import com.github.ricardobaumann.spring_blog_frontend.helpers.SessionHelper;
import com.github.ricardobaumann.spring_blog_frontend.pageforms.LoginForm;
import com.github.ricardobaumann.spring_blog_frontend.pageforms.PersonForm;
import com.github.ricardobaumann.spring_blog_frontend.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

/**
 * Created by ricardobaumann on 22/11/16.
 */
@Controller
public class LoginController extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/loginresults").setViewName("loginresults");
    }

    @GetMapping("/login")
    public String showLoginForm(LoginForm loginForm) {
        return "login";
    }

    @PostMapping("/login")
    public String checkLoginInfo(@Valid LoginForm loginForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        UserDTO userDTO = null;//loginService.login(loginForm.getUsername(),loginForm.getPassword());
        if (userDTO!=null) {
            sessionHelper.setUserDTO(userDTO);
        }

        return "redirect:/loginresults";
    }
}
