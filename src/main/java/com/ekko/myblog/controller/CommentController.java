package com.ekko.myblog.controller;

import com.ekko.myblog.exception.NotFoundException;
import com.ekko.myblog.pojo.Comment;
import com.ekko.myblog.pojo.User;
import com.ekko.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @GetMapping("/comments/{blogid}")
    public String comments(@PathVariable Long blogid, Model model){
        //所有的根评论
        List<Comment> comments = commentService.listCommentByBlogid(blogid);
        //根据根评论,完成查询所有的子评论
        commentService.getSonComments(comments);
        model.addAttribute("comments",comments);
        return "blog::commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment,HttpSession session){
        Long getid=System.currentTimeMillis();
        int t=0;
        while (commentService.getCommentById(getid)!=null ){
            t++;
            if (t>7){
                throw new NotFoundException("请求超时!");
            }
            getid=System.currentTimeMillis();
        }
        comment.setId(getid);
        comment.setCreateTime(new Timestamp(getid));
        User user = (User)session.getAttribute("user");
        if(user !=null){
            comment.setAdminComment(true);
            comment.setAvatar(user.getAvatar());
        }else{
            comment.setAvatar("/images/userAvatar.png");
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+comment.getBlogid();
    }

}
