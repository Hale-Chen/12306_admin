package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.AdminService;
import com.example.demo.util.HttpUtil;
import com.example.demo.util.ResponseDataUtil;
import com.example.demo.util.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminController
 * @Description TODO
 * @Author FeiChen
 * @Date 2018/12/18 14:52
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
    *@Author FeiChen
    *@Description 获取订单列表
    *@Date 11:10 2018/12/19
    *@Param [request, response]
    *@return java.util.Map
    **/
    @RequestMapping("selectOrderList")
    public Map selectOrderList(HttpServletRequest request, HttpServletResponse response){
        String mobilePhone = request.getParameter("mobilePhone");
        String date = request.getParameter("date");
        List<Map> result =  adminService.selectOrderList(date,mobilePhone);

        Map resultMap =  ResponseDataUtil.responseData(result);
        resultMap.put("total",result.size());
        return resultMap;
    }

    /**
    *@Author FeiChen
    *@Description 删除订单
    *@Date 11:11 2018/12/19
    *@Param [request, response]
    *@return java.util.Map
    **/
    @RequestMapping("deleteOrderById")
    public Map deleteOrderById(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        if(StringUtils.isEmpty(id)){
            throw new CustomException("10001","订单ID不能为空");
        }
        int result = adminService.deleteOrderByid(Integer.parseInt(id));
        if(result<=0){
            throw new CustomException("10002","删除订单失败");
        }
        return ResponseDataUtil.responseData(result);
    }

    /**
    *@Author FeiChen
    *@Description 发送短信
    *@Date 11:14 2018/12/19
    *@Param
    *@return
    **/
    @RequestMapping("sendMsg")
    public Map sendMsg(HttpServletRequest request, HttpServletResponse response){
        String username = "xiechengdp";
        String password = "lHlh1eE3";
        String mobile = request.getParameter("mobilePhone");
        String content = request.getParameter("content");
        String xh = request.getParameter("xh");
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(content)){
            throw new CustomException("10003","手机号或内容不能为空");
        }
        Map map = new HashMap();
        map.put("username",username);
        map.put("password",password);
        map.put("content",content);
        map.put("mobile",mobile);
        map.put("xh",xh);
        String jsonString = JSON.toJSONString(map);
        String url = "http://114.215.196.145/sendSmsApi?username="+username+"&password="+password+"&mobile="+mobile+"&content="+content+"&xh="+xh;
        String result = HttpUtil.postRequest(url,jsonString,"UTF-8");
        System.out.println(result);
        return ResponseDataUtil.responseData(result);
//        JSONObject resultJson = JSON.parseObject(result);

    }

}
