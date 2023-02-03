package com.ekko.myblog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class adminLogAspect {
    private final Logger logger=  LoggerFactory.getLogger(this.getClass());
    @Pointcut("execution(* com.ekko.myblog.controller.adminController.*.*(..))")
    public void log(){
    }

    @Before("log()")
    public void dobe(JoinPoint joinPoint){
        logger.info("-----admin-began----------");
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String methodName = joinPoint.getSignature().getName();
        //获取请求参数
        logger.info("url:{}  ip:{}   methodName:{}",url,ip,methodName);
    }
    @AfterReturning(pointcut = "log()",returning = "result")
    public void doAfterRetrun(JoinPoint joinPoint,Object result){
        logger.info(" result:{}",result);
        logger.info("-----admin-end-----------");
    }
}
