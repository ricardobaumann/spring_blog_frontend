package com.github.ricardobaumann.spring_blog_frontend.pageforms;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ricardobaumann on 23/11/16.
 */
@Data
public class PostsForm {

    @NotEmpty
    private String title;

}
