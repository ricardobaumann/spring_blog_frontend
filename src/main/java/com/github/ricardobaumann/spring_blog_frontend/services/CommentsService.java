package com.github.ricardobaumann.spring_blog_frontend.services;

import com.github.ricardobaumann.spring_blog_frontend.configuration.CustomAuthentication;
import com.github.ricardobaumann.spring_blog_frontend.helpers.BackendHelper;
import com.github.ricardobaumann.spring_blog_frontend.models.Comment;
import com.github.ricardobaumann.spring_blog_frontend.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ricardobaumann on 28/11/16.
 */
@Service
public class CommentsService {

    @Autowired
    private BackendHelper backendHelper;

    public Comment createComment(Post post, Comment comment, CustomAuthentication customAuthentication) {
        String path = String.format("/posts/%s/comments",post.getId());
        return backendHelper.post(comment, path,customAuthentication.getToken(),Comment.class);
    }

}
