package com.ekko.myblog.interceptor;

import com.ekko.myblog.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author yll
 * @Date 2023/3/11 8:04
 * @PackageName:com.ekko.myblog.interceptor
 * @ClassName: UserInterceptor
 * @Description: 实现网站访问量的统计,最初统计是通过切面去实现的,统计数据不合理.
 * @Version 1.0
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    InformationService informationService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String visit = (String)session.getAttribute("visit");
        if (!("1".equals(visit))){
            //没有访问过了
            informationService.increase();
            session.setAttribute("visit","1");
        }
        return true;
    }
}
