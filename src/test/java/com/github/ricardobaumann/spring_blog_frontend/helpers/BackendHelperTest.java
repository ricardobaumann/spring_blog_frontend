package com.github.ricardobaumann.spring_blog_frontend.helpers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        System.out.println(backendHelper.getToken("roy","spring"));
    }

}