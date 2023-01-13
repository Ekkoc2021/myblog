package com.ekko.myblog.handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ContrllerExceptionHandler {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public String exceptionHander(HttpServletRequest request, Exception e, Model m) throws Exception {
        logger.error("Request URL : {},Exception : {}" +request.getRequestURL(),e);
        //如果得到的异常我们自定义的异常,交给spring处理,根据响应跳到指定页面
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)==null){
            throw e;
        }
        m.addAttribute("url",request.getRequestURL());
        m.addAttribute("exception",e);
        return "error/error";
    }
}
