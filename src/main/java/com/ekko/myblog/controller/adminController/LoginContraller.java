package com.ekko.myblog.controller.adminController;

import com.ekko.myblog.pojo.User;
import com.ekko.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginContraller {
    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    @Autowired
    UserService userService;

    /**
    * @description:
    * @author yll
    * @date 2023/3/10 19:42
    * @param
    *  session:
    *  model:
    *  redirectAttributes:
    *  username:
    *  password:
    * @return java.lang.String
    */
    @PostMapping("/login")
    public String login(HttpSession session, Model model, RedirectAttributes redirectAttributes, @RequestParam String username, @RequestParam String password) {
        User user = userService.CheckUser(username, password);
        if (user != null) {
            user.setPassword(null);//页面拿到密码不安全
            session.setAttribute("user", user);
            model.addAttribute("username", user.getNickname());
            return "admin/index";
        }
        //要用redirectAttributes实现重定向携带数据,model中返回数据后不会携带
        redirectAttributes.addFlashAttribute("message", "用户名或者密码错误");
        return "redirect:/admin";//重定向,直接跳转会出现问题
    }

    //每次不小心访问/admin/login都会打印错误日志
    @GetMapping("/login")
    public String tologin() {
        return "redirect:/admin";//重定向,直接跳转会出现问题
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
