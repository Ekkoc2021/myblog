package com.ekko.myblog.pojo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private Timestamp createTime;
    private Timestamp updateTime;
    private List<Blog> blogs = new ArrayList<>();
}
