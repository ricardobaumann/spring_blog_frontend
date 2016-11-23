package com.github.ricardobaumann.spring_blog_frontend;

import com.github.ricardobaumann.spring_blog_frontend.configuration.CustomAuthentication;
import com.github.ricardobaumann.spring_blog_frontend.helpers.BackendHelper;
import com.github.ricardobaumann.spring_blog_frontend.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Created by ricardobaumann on 23/11/16.
 */
@Service
public class PostService {

    @Autowired
    private BackendHelper backendHelper;

    public Post createPost(Post post, CustomAuthentication customAuthentication) {
        return backendHelper.post(post,"posts",customAuthentication.getToken(),Post.class);
    }

}
