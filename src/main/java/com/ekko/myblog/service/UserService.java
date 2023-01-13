package com.ekko.myblog.service;

import com.ekko.myblog.pojo.Blog;
import com.ekko.myblog.pojo.User;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;


public interface UserService {
    User CheckUser(String username,String password);

    void setBlogUser(Blog blog);

    void setBlogsUser(PageInfo<Blog> pageInfo);

    User selectUserById(Long userId);
}
