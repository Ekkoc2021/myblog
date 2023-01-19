package com.ekko.myblog.controller.adminController;

import com.ekko.myblog.pojo.Tag;
import com.ekko.myblog.pojo.User;
import com.ekko.myblog.service.BlogTagService;
import com.ekko.myblog.service.TagService;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.DocFlavor;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;
    @GetMapping("/tags/{pageNum}")
    public String tags(@PathVariable("pageNum")int pageNum, Model model){
        //查找所有的id
        PageInfo<Tag> page = tagService.allTags(pageNum);
        //放入model
        model.addAttribute("page",page);
        //返回渲染页面
        return "admin/tags";
    }

    @GetMapping("tags/input")
    public String addTags(){
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String addTags(@RequestParam("name") String tagName,
                          HttpSession session,
                          RedirectAttributes redirectAttributes){
        User user = (User) session.getAttribute("user");
        Tag tag=new Tag();
        tag.setId(user.getId()+System.currentTimeMillis());
        tag.setName(tagName);
        try {
            tagService.addTags(tag);
            redirectAttributes.addFlashAttribute("message","添加标签成功!");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","添加失败!标签名称重复。");
        }
        return "redirect:/admin/tags/1";
    }

    @GetMapping("/tags/{id}/{name}/{pagenum}")
    public String updateTags(@PathVariable("pagenum") int pagenum,@PathVariable("id") Long id,@PathVariable("name") String name,Model model){
        model.addAttribute("id",id);
        model.addAttribute("tname",name);
        model.addAttribute("backPage",pagenum);
        return "admin/tags-input";
    }
    @Autowired
    BlogTagService blogTagService;
    @PostMapping("/tags/{id}/{backPage}")
    public String updateTags(@PathVariable int backPage,@PathVariable Long id, @RequestParam String name,RedirectAttributes redirectAttributes){
        try {
            //更新blog_tag表
            tagService.updateTags(id,name);
            redirectAttributes.addFlashAttribute("message","修改成功!");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","修改失败!标签名称重复。");
        }
        return "redirect:/admin/tags/"+backPage;
    }

//    http://localhost:8080/admin/tags/deleteTag/24/1/delete
    @GetMapping("/tags/deleteTag/{id}/{backPage}/delete")
    @Transactional
    public String delete(@PathVariable int backPage,@PathVariable Long id,RedirectAttributes redirectAttributes){
        try {
            blogTagService.deleteByTagid(id);
            tagService.deleteTags(id);
            redirectAttributes.addFlashAttribute("message","删除成功!");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","删除失败!请重试。");
        }
        return "redirect:/admin/tags/"+backPage;
    }

}
