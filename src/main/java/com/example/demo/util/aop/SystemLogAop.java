package com.example.demo.util.aop;

import com.alibaba.fastjson.JSON;
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
import java.util.Map;


/**
 * @ClassName SystemLogAop
 * @Description 系统日志AOP
 * @Author FeiChen
 * @Date 2018/6/5 23:04
 **/

@Component
@Aspect
public class SystemLogAop {

    private static final Logger logger = LoggerFactory.getLogger(SystemLogAop.class);

    /**
    *@Author FeiChen
    *@Description 切入点定义：扫描com.yunji.visitor_system包下及子包下并且带有SystemLogAnnotation注解的方法
    *@Date 10:57 2018/6/6
    *@Param []
    *@return void
    **/
    @Pointcut("execution(* com.yunji.attendance_system..*.*(..)) && @annotation(SystemLogAnnotation)")
    public void execute(){}

    /**
    *@Author FeiChen
    *@Description 方法执行前处理
    *@Date 11:01 2018/6/6
    *@Param [joinPoint]
    *@return void
    **/
    @Before("execute()")
    public void doMethodBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //打印请求内容
        logger.info("===============请求内容===============");
        logger.info("请求地址:"+request.getRequestURL().toString());
        logger.info("请求方式:"+request.getMethod());
        logger.info("请求类方法:"+joinPoint.getSignature());
        Map parameterMap = request.getParameterMap();
        logger.info("请求参数："+JSON.toJSONString(parameterMap));
    }

    /**
    *@Author FeiChen
    *@Description 方法返回参数后处理
    *@Date 11:15 2018/6/6
    *@Param [object]
    *@return void
    **/
    @AfterReturning(returning = "object",pointcut = "execute()")
    public void doMethodAfterReturning(Object object){
        logger.info("===============返回内容===============");
        logger.info("response内容："+JSON.toJSONString(object));
    }
}
