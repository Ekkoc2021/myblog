package com.ekko.myblog.config;

import com.ekko.myblog.interceptor.CommInterceptor;
import com.ekko.myblog.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {
   @Autowired
   LoginInterceptor loginInterceptor;
   @Autowired
    CommInterceptor commInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).
                addPathPatterns("/admin/**").
                excludePathPatterns("/admin","/admin/login");
        registry.addInterceptor(commInterceptor).addPathPatterns("/index/hhh");
    }
}
