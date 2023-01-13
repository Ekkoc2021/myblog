package com.ekko.myblog.service;

import com.ekko.myblog.pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listCommentByBlogid(long blogid);
    int saveComment(Comment comment);
    void getSonComments(List<Comment> comments);

    Comment getCommentById(long currentTimeMillis);
}
