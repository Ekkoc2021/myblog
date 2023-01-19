package com.ekko.myblog.controller.adminController;

import com.ekko.myblog.exception.NotFoundException;
import com.ekko.myblog.pojo.Blog;
import com.ekko.myblog.pojo.BlogTag;
import com.ekko.myblog.pojo.User;
import com.ekko.myblog.service.BlogService;
import com.ekko.myblog.service.BlogTagService;
import com.ekko.myblog.util.Converter;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class DiaryController {
    @Autowired
    BlogService blogService;

    @RequestMapping("/allDiarys/{pageNum}")
    public String mydiarys(@PathVariable int pageNum, Model model) {
        //todo:查看日记界面
        if (pageNum < 0) {
            pageNum = 1;
        }
        PageInfo<Blog> diarys = blogService.getDiarys(pageNum);
        model.addAttribute("page", diarys);
        return "admin/diary";
    }

    @RequestMapping("/writeDiary")
    public String toInputHtml() {
        return "admin/inputDiary";
    }

    @Autowired
    BlogTagService blogTagService;

    @PostMapping("/addDiary")
    @Transactional
    public String addBlog(Blog blog, RedirectAttributes redirectAttributes, HttpSession session) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        blog.setCreateTime(timestamp);
        blog.setUpdateTime(timestamp);
        blog.setId(System.currentTimeMillis());
        User user = (User) session.getAttribute("user");
        blog.setViews(0);
        blog.setUserId(user.getId());
        if (blog.getTypename() == null || "".equals(blog.getTypename())) {
            //这里应该检查一下数据库中是否有日记这个类型,自用就不查了
            blog.setTypename("日记");
        }
        if (blog.getFlag() == null || "".equals(blog.getFlag())) {
            blog.setFlag("原创");
        }
        blog.setViews(0);
        blogService.addBlogs(blog);
        redirectAttributes.addFlashAttribute("message", "更新成功!");
        return "redirect:/admin/allDiarys/1";//重定向到blogs页面
    }
}

