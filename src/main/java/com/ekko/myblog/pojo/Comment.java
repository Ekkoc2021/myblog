package com.ekko.myblog.pojo;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"parentComment"})
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Timestamp createTime;
    private Blog blog;
    private Long blogid;
    //1:n
    private List<Comment> replyComments = new ArrayList<>();
    //n:1
    private Comment parentComment;
    private  Long parentCommentid;
    private boolean adminComment;

}
