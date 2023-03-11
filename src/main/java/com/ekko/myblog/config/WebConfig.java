package com.ekko.myblog.config;

import com.ekko.myblog.interceptor.CommInterceptor;
import com.ekko.myblog.interceptor.LoginInterceptor;
import com.ekko.myblog.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
* @description: 用于配置拦截器
*/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;
    @Autowired
    CommInterceptor commInterceptor;

    @Autowired
    UserInterceptor userInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截器
        registry.addInterceptor(loginInterceptor).
                addPathPatterns("/admin/**").
                excludePathPatterns("/admin", "/admin/login");
        //很奇怪的访问:拦截掉
        registry.addInterceptor(commInterceptor).addPathPatterns("/index/hhh");
        //访问量统计的拦截器
        registry.addInterceptor(userInterceptor).addPathPatterns
                ("/index/**"
                 ,"/blog/**"
                 ,"/types/**"
                 ,"/archives/**"
                 ,"/comments/**"
                 ,"/tags/**"
                 ,"/about/**");
    }
}
