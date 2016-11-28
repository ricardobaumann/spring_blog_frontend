package com.github.ricardobaumann.spring_blog_frontend.pageforms;

import com.github.ricardobaumann.spring_blog_frontend.models.Post;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ricardobaumann on 28/11/16.
 */
@Data
public class CommentsForm {

    private Post post;

    @NotEmpty
    private String content;

    private Long id;

}
