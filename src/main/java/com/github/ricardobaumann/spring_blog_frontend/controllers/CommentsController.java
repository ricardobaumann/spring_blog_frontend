package com.github.ricardobaumann.spring_blog_frontend.controllers;

import com.github.ricardobaumann.spring_blog_frontend.PostService;
import com.github.ricardobaumann.spring_blog_frontend.configuration.CustomAuthentication;
import com.github.ricardobaumann.spring_blog_frontend.helpers.Converter;
import com.github.ricardobaumann.spring_blog_frontend.models.Comment;
import com.github.ricardobaumann.spring_blog_frontend.models.Post;
import com.github.ricardobaumann.spring_blog_frontend.pageforms.CommentsForm;
import com.github.ricardobaumann.spring_blog_frontend.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by ricardobaumann on 28/11/16.
 */
@Controller
public class CommentsController {

    @Autowired
    private PostService postService;

    @Autowired
    private Converter converter;

    @Autowired
    private CommentsService commentsService;

    @GetMapping("/posts/{post_id}/comments")
    public String getComments(@PathVariable("post_id") Long postId,
                              CommentsForm commentsForm,
                              CustomAuthentication customAuthentication) {

        Post post = postService.getPost(postId, customAuthentication);
        commentsForm.setPost(post);

        commentsForm.setContent("Input your comment");
        commentsForm.setComments(commentsService.getComments(post,customAuthentication));

        return "comments";
    }

    @PostMapping("/posts/{post_id}/comments")
    public String postComments(@PathVariable("post_id") Long postId,
                               CommentsForm commentsForm,
                               CustomAuthentication customAuthentication,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        Post post = postService.getPost(postId, customAuthentication);
        Comment comment = converter.convert(commentsForm,Comment.class);
        comment = commentsService.createComment(post,comment,customAuthentication);
        commentsForm.setId(comment.getId());

        redirectAttributes.addFlashAttribute(String.format("message","Comment created with id %s",comment.getId()));

        return String.format("redirect:/posts/%s/comments",postId);


    }

    @GetMapping("/comments/delete")
    public String deleteComments(Long id, Long postId, CustomAuthentication customAuthentication) {

        commentsService.delete(postId,id, customAuthentication);

        return String.format("redirect:/posts/%s/comments",postId);
    }

}
