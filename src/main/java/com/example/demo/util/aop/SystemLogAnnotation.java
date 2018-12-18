package com.example.demo.util.aop;

import java.lang.annotation.*;

/**
 * @ClassName SystemLogAnnotation
 * @Description 日志打印注解
 * @Author FeiChen
 * @Date 2018/6/5 22:59
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLogAnnotation {
    String value() default "";
}
