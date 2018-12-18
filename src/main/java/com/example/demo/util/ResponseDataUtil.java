package com.example.demo.util;

import com.example.demo.util.exception.ErrorCode;
import com.example.demo.util.exception.ErrorMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ResponseDataUtil
 * @Description 统一返回数据格式
 * @Author FeiChen
 * @Date 2018/6/7 9:51
 **/
public class ResponseDataUtil {

    public static Map responseData(Object data){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("success",true);
        resultMap.put("errorCode",ErrorCode.RESPONSE_SUCCESS);
        resultMap.put("errorMsg",ErrorMessage.RESPONSE_SUCCESS);
        resultMap.put("data",data);
        return resultMap;
    }

    public static Map responseData(Object data,String errorCode,String errorMsg,boolean success){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("success",success);
        resultMap.put("errorCode",errorCode);
        resultMap.put("errorMsg",errorMsg);
        resultMap.put("data",data);
        return resultMap;
    }
}
