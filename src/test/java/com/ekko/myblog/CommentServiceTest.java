package com.ekko.myblog;

import com.ekko.myblog.pojo.Comment;
import com.ekko.myblog.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class CommentServiceTest {
    @Autowired
    CommentService commentService;
    @Test
    public void testGetSon(){
//        List<Comment> comments = commentService.listCommentByBlogid(1673007960779l);
//        commentService.getSonComments(comments);
        System.out.println(  UUID.randomUUID().toString());
    }

    @Test
    public void testGETbyid(){
        System.out.println(commentService.getCommentById(1234l));
    }

}
