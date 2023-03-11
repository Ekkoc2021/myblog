package com.ekko.myblog.aspect;

import com.ekko.myblog.service.InformationService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.ekko.myblog.controller.*.*(..))")
    public void log() {
    }



    @Before("log()")
    public void dobe(JoinPoint joinPoint) {
        logger.info("-----user-began----------");
        /*
         * 调用一下访问量增加的方法,但有时候一次访问会调用几个controller方法,T_T
         * 用redis性能会更好,每次都经过数据库
         *
         * */
//

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String methodName = joinPoint.getSignature().getName();
        //获取请求参数
        Object[] args = joinPoint.getArgs();
        logger.info("url:{}  ip:{}  args:{}  methodName:{}", url, ip, args, methodName);
    }

    //    @After("log()")
//    public void doAf(){
//        logger.info("hl");
//    }
    @AfterReturning(pointcut = "log()", returning = "result")
    public void doAfterRetrun(JoinPoint joinPoint, Object result) {
        logger.info(" result:{}", result);
        logger.info("-----user-end----------");
    }
}
