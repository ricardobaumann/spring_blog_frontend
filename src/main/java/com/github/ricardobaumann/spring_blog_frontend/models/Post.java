package com.github.ricardobaumann.spring_blog_frontend.models;

import lombok.Data;

/**
 * Created by ricardobaumann on 23/11/16.
 */
@Data
public class Post {

    private String category;

    private String title;

    private String content;

    private Long id;

    private Comment[] comments;
}
