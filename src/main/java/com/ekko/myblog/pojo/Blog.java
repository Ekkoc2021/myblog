package com.ekko.myblog.pojo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Blog {
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String typename;
    private Long userId;
    //多对多关系
    private List<Tag> tags = new ArrayList<>();
    private User user;
    //一对多关系
    private List<Comment> comments = new ArrayList<>();
    private String tagIds;
    private String description;

}
