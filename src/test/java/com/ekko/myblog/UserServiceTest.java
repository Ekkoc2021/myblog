package com.ekko.myblog;

import com.ekko.myblog.util.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Test
    public void passWordTest(){
        System.out.println(MD5Utils.code("qwe456654."));
    }
}
