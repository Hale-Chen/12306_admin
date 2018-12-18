package com.example.demo.util.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CustomControllerAdvice
 * @Description global exception handler
 * @Author FeiChen
 * @Date 2018/6/5 16:31
 **/
@RestControllerAdvice
public class CustomControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(CustomControllerAdvice.class);

    /**
    *@Author FeiChen
    *@Description 全局异常捕捉处理
    *@Date 17:01 2018/6/5
    *@Param [e]
    *@return java.util.Map
    **/
    @ExceptionHandler(value = Exception.class)
    public Map exceptionHandler(Exception e){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("success",false);
        map.put("errorCode",ErrorCode.GLOBAL_ERROR_CODE);
        map.put("errorMsg",e.getMessage());
        map.put("data",null);
        logger.error("全局异常：",e);
        return map;
    }

    /**
    *@Author FeiChen
    *@Description 自定义异常捕捉
    *@Date 20:03 2018/6/5
    *@Param [e]
    *@return java.util.Map
    **/
    @ExceptionHandler(value = CustomException.class)
    public Map customExceptionHandler(CustomException e){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("success",false);
        map.put("errorCode",e.getCode());
        map.put("errorMsg",e.getMsg());
        map.put("data",null);
        logger.error("自定义异常",e);
        return map;
    }
}
