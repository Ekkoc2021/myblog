package com.ekko.myblog.controller;

import com.ekko.myblog.exception.NotFoundException;
import com.ekko.myblog.pojo.Blog;
import com.ekko.myblog.pojo.Information;
import com.ekko.myblog.service.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;

    @Autowired
    InformationService informationService;

    @GetMapping({"/index", "/"})
    public String index0() {
        return "redirect:/index/1";
    }

    @Autowired
    UserService userService;

    @GetMapping("/index/{pageNum}")
    public String index(@PathVariable int pageNum, Model model) {
        Information information = informationService.getInformation();
        //user,
        PageInfo<Blog> pageInfo = blogService.listPulbishedBlog(pageNum);
        userService.setBlogsUser(pageInfo);
        tagService.setBlogTags(pageInfo);
        model.addAttribute("page", pageInfo);
        model.addAttribute("information", information);
        model.addAttribute("types", typeService.listPageIndex(1));
        model.addAttribute("tags", tagService.listPageIndex(1));
        model.addAttribute("recommendBlogs", blogService.listRecom(1));
        return "index";
    }

    @GetMapping("/about")
    public String tags(Model m) {
        return "about";
    }

    @PostMapping("/search")
    public String search(@RequestParam String query, Model model) {
        return search0(query, model, 1);
    }

    @GetMapping("/search/{query}/{pagenum}")
    public String seach2(@PathVariable String query, Model model, @PathVariable int pagenum) {
        return search0(query, model, pagenum);
    }

    public String search0(String search, Model model, int pagenum) {
        PageInfo<Blog> pageInfo = blogService.searchBlog(search, pagenum);
        userService.setBlogsUser(pageInfo);
        tagService.setBlogTags(pageInfo);
        model.addAttribute("page", pageInfo);
        model.addAttribute("query", search);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blogDetail(@PathVariable Long id, Model model) {
        Blog blog = blogService.getPublishedBlogAndConvert(id);
        if (blog == null) {//查询到的博客为空!
            throw new NotFoundException("博客不存在或者尚未发布!");
        }
        userService.setBlogUser(blog);
        blog.setTags(tagService.getTagsByBlogId(blog.getId()));
        model.addAttribute("blog", blog);
        //更新博客的浏览记录
        blogService.viewAdd(id);
        return "blog";
    }

}
