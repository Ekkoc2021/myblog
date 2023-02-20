package com.ekko.myblog.service.serviceImpl;

import com.ekko.myblog.Mappers.CommentMapper;
import com.ekko.myblog.pojo.Comment;
import com.ekko.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class CommentServiceImpl implements CommentService {

    public void queueAddlist(List<Comment> comments, Queue<Comment> queue) {
        for (Comment c : comments) {
            queue.add(c);
        }
    }

    public void getSonComments(List<Comment> comments) {
        Queue<Comment> queue = new LinkedList<>();
        queueAddlist(comments, queue);
        while (true) {
            if (queue.isEmpty()) {
                break;
            }
            Comment poll = queue.remove();
            List<Comment> comments1 = commentMapper.getSon(poll.getId());
            queueAddlist(comments1, queue);
            poll.setReplyComments(comments1);
            setParent(comments1, poll);
        }
    }

    public void setParent(List<Comment> comments, Comment parentComment) {
        for (Comment c : comments) {
            c.setParentComment(parentComment);
        }
    }

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> listCommentByBlogid(long blogid) {
        return commentMapper.selectCommentByBlogid(blogid);

    }

    @Override
    public int saveComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    @Override
    public Comment getCommentById(long currentTimeMillis) {
        return commentMapper.selectCommentById(currentTimeMillis);
    }
}
