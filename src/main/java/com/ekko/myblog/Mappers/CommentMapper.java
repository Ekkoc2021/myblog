package com.ekko.myblog.Mappers;

import com.ekko.myblog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> selectCommentByBlogid(@Param("blogid") long blogid);

    List<Comment> getSon(@Param("parentid") Long id);

    int insertComment(@Param("comment") Comment comment);

    Comment selectCommentById(@Param("id") long currentTimeMillis);
}
