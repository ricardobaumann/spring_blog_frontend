package com.github.ricardobaumann.spring_blog_frontend.helpers;

import com.github.ricardobaumann.spring_blog_frontend.models.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

/**
 * Created by ricardobaumann on 22/11/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendHelperTest {

    @Autowired
    private BackendHelper backendHelper;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void post() throws Exception {
       OAuth2AccessToken token = backendHelper.getToken("roy","spring");
        Post post = new Post();
        post.setCategory("category");
        post.setContent("content");
        post.setTitle("title");
        post = backendHelper.post(post, "posts",token.getValue(),Post.class);
        assertNotNull(post.getId());
    }

}