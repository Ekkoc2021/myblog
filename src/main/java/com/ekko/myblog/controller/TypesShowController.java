package com.ekko.myblog.controller;

import com.ekko.myblog.exception.NotFoundException;
import com.ekko.myblog.pojo.Blog;
import com.ekko.myblog.pojo.Type;
import com.ekko.myblog.service.BlogService;
import com.ekko.myblog.service.TagService;
import com.ekko.myblog.service.TypeService;
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
@RequestMapping("/types")
public class TypesShowController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;

    @GetMapping({"/{id}"})
    public String types(@PathVariable Long id, Model model) {
        List<Type> types = typeService.listShowType();
        model.addAttribute("types", types);
        //还要加入查询到的blog对象放入,一开始穿过了typeid=-1的,要默认是博客数量最大的type的id
        if (id == -1) {
            if (types.size() > 0) {
                id = types.get(0).getId();
            } else {
                throw new NotFoundException("博主还没有任何博客!请联系博主更新!");
            }
        }
        PageInfo<Blog> pageInfo = blogService.listBlogbByTypeId(id, 1);
        userService.setBlogsUser(pageInfo);
        tagService.setBlogTags(pageInfo);
        model.addAttribute("page", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }

    @Autowired
    UserService userService;
    @Autowired
    TagService tagService;

    @GetMapping("/{id}/{pagenum}")
    //这里可以和上一个方法合并一下,但是我懒得改前端代码了
    public String showTypesBLogPage(@PathVariable Long id, @PathVariable int pagenum, Model model) {
        List<Type> types = typeService.listShowType();
        model.addAttribute("types", types);
        //还要加入查询到的blog对象放入,一开始穿过了typeid=-1的,要默认是博客数量最大的type的id
        PageInfo<Blog> pageInfo = blogService.listBlogbByTypeId(id, pagenum);
        userService.setBlogsUser(pageInfo);
        tagService.setBlogTags(pageInfo);
        model.addAttribute("page", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }

}
