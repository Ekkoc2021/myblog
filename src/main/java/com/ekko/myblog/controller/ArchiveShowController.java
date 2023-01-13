package com.ekko.myblog.controller;

import com.ekko.myblog.pojo.Blog;
import com.ekko.myblog.service.BlogService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ArchiveShowController {
    @Autowired
    BlogService blogService;
    @GetMapping("/archives")
    public String archives(Model model){
        //填入的数据类型是Map<String,List<Blog>>
        Map<String, List<Blog>> map = blogService.mapArchives();
        model.addAttribute("archiveMap",map);
        model.addAttribute("blogCount",blogService.getBlogsCount());
        return "archives";
    }
}
