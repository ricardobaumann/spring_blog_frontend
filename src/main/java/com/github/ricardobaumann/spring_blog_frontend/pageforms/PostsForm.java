package com.github.ricardobaumann.spring_blog_frontend.pageforms;

import com.github.ricardobaumann.spring_blog_frontend.models.Post;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ricardobaumann on 23/11/16.
 */
@Data
public class PostsForm {

    @NotEmpty
    private String title;

    @NotEmpty
    private String category;

    @NotEmpty
    private String content;

    private Long id;

    private Post[] posts;

}
