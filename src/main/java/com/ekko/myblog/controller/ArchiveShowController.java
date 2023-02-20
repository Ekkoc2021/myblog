package com.ekko.myblog.controller;

import com.ekko.myblog.pojo.Blog;
import com.ekko.myblog.service.BlogService;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
public class ArchiveShowController {
    @Autowired
    BlogService blogService;

    /**
     * 重定向作用
     *
     * @return
     */
    @GetMapping("/archives")
    public String archives0() {
        return "redirect:/archives/1";
    }

    /**
     * 获取对应页码的年份并返回
     *
     * @param model
     * @param num
     * @return
     */
    @GetMapping("/archives/{num}")
    public String archives(Model model, @PathVariable int num) {
        //填入的数据类型是Map<String,List<Blog>>
        if (num < 1) {
            num = 1;
        }
        PageInfo<String> archivesYear = blogService.getArchivesYear(num);
        model.addAttribute("page", archivesYear);
        return "archives";
    }

    /**
     * 查询对应年份的对应页码的数据
     *
     * @param year    年份
     * @param pagenum 页码
     * @param model
     * @return
     */
    @GetMapping("/archives/detail/{year}/{pagenum}")
    public String archivesDetail(@PathVariable("year") String year, @PathVariable("pagenum") int pagenum, Model model) {
        model.addAttribute("yaar", year);
        PageInfo<Blog> archivesDetail = blogService.getArchivesDetail(pagenum, year);
        model.addAttribute("page", archivesDetail);
        return "archivesDetail";
    }
}
