package com.ekko.myblog.controller.adminController;

import com.ekko.myblog.exception.NotFoundException;
import com.ekko.myblog.pojo.Type;
import com.ekko.myblog.pojo.User;
import com.ekko.myblog.service.BlogService;
import com.ekko.myblog.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types/{pageNum}")
    public String types(@PathVariable(value = "pageNum") int pageNum, Model model) {
        PageInfo<Type> typePage = typeService.listPage(pageNum);
        model.addAttribute("page", typePage);
        return "admin/types";
    }

    //跳转到提交界面
    @GetMapping("/types/input")
    public String inputType() {
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(Type type, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        type.setId(user.getId() + System.currentTimeMillis());
        try {
            if (type != null && type.getName() != null && !"".equals(type.getName()) && typeService.saveType(type) != null) {
                //成功
                redirectAttributes.addFlashAttribute("message", "操作成功!");
            } else {
                //失败
                redirectAttributes.addFlashAttribute("message", "操作失败!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "分类已经存在!");
        }

        return "redirect:/admin/types/1";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("type", typeService.getType(id));//验证数据是否存在
        return "admin/types-input";
    }

    @Autowired
    BlogService blogService;

    @PostMapping("/types/{id}")
    public String updatepost(@PathVariable Long id, Type type, RedirectAttributes redirectAttributes) {
        try {
            //更新对应的博客类型名称
            Type type1 = typeService.getType(id);
            if (type1 == null) {
                throw new NotFoundException("id对应的类型不存在!");
            }
            String name = type1.getName();
            blogService.updateBlogsTypename(name, type.getName());
            typeService.updateType(id, type);
            redirectAttributes.addFlashAttribute("message", "更新成功!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "更新失败!分类名称重复!");
        }
        return "redirect:/admin/types/1";
    }

    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        typeService.deleteType(id);
        redirectAttributes.addFlashAttribute("message", "删除成功!");
        return "redirect:/admin/types/1";
    }

}
