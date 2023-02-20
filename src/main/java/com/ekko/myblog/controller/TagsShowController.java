package com.ekko.myblog.controller;

import com.ekko.myblog.exception.NotFoundException;
import com.ekko.myblog.pojo.Blog;
import com.ekko.myblog.pojo.Tag;
import com.ekko.myblog.service.BlogService;
import com.ekko.myblog.service.TagService;
import com.ekko.myblog.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagsShowController {

    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public String showTagsAndBlogs(@PathVariable Long id, Model model) {
        List<Tag> tags = tagService.listTagsWithBlogsize();
        if (id == -1) {
            if (tags.size() > 0) {
                id = tags.get(0).getId();
            } else {
                throw new NotFoundException("还没有任何博客!请联系作者更新!");
            }
        }
        model.addAttribute("tags", tags);
        PageInfo list = blogService.listBlogByTagId(id, 1);
        userService.setBlogsUser(list);
        tagService.setBlogTags(list);
        model.addAttribute("page", list);
        model.addAttribute("activeTagId", id);
        return "tags";
    }

    @GetMapping("/{id}/{pageNum}")
    public String showTagsAndBlogsWithPageNum(@PathVariable Long id, Model model, @PathVariable int pageNum) {
        List<Tag> tags = tagService.listTagsWithBlogsize();
        model.addAttribute("tags", tags);
        PageInfo<Blog> list = blogService.listBlogByTagId(id, pageNum);
        userService.setBlogsUser(list);
        tagService.setBlogTags(list);
        model.addAttribute("page", list);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
