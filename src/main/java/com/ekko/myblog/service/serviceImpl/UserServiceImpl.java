package com.ekko.myblog.service.serviceImpl;

import com.ekko.myblog.Mappers.UserMapper;
import com.ekko.myblog.pojo.Blog;
import com.ekko.myblog.pojo.User;
import com.ekko.myblog.service.UserService;
import com.ekko.myblog.util.MD5Utils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User CheckUser(String username, String password) {
        return userMapper.findByUsernameAndUser(username, MD5Utils.code(password));

    }

    @Override
    public void setBlogUser(Blog blog) {
        User user = selectUserById(blog.getUserId());
        user.setPassword("");
        blog.setUser(user);
    }

    @Override
    public void setBlogsUser(PageInfo<Blog> pageInfo) {
        List<Blog> list = pageInfo.getList();
        for (Blog b : list) {
            setBlogUser(b);
        }
    }

    @Override
    public User selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }
}
