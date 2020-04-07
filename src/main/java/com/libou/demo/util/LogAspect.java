package com.libou.demo.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.libou.demo.controller..*.*(..))")
    public void controllerLog(){};

    @Before("controllerLog()")
    public void logBeforeController(JoinPoint joinPoint){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();

//        logger.info("################URL : " + request.getRequestURL().toString());
//        logger.info("################HTTP_METHOD : " + request.getMethod());
//        logger.info("################IP : " + request.getRemoteAddr());
//        logger.info("################THE ARGS OF THE CONTROLLER : " + Arrays.toString(joinPoint.getArgs()));

        // url
        logger.info("url={}", request.getRequestURL());
        // method(Get/Post...)
        logger.info("method={}", request.getMethod());
        // ip
        logger.info("ip={}", request.getRemoteAddr());
        // 类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        // 参数
        logger.info("args={}", Arrays.asList(joinPoint.getArgs()));

        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        logger.info("Function: " + methodName + ", params: " + args);
        System.out.println("Function: " + methodName + ", params: " + args);
    }

    @AfterReturning(pointcut = "controllerLog()", returning = "returnObj")
    public void logAfterController(JoinPoint joinPoint, Object returnObj){
        logger.info("##################### the return of the method is : " + returnObj);
        System.out.println("##################### the return of the method is : " + returnObj);
    }

}
