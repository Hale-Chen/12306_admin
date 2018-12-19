package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.AdminService;
import com.example.demo.util.HttpUtil;
import com.example.demo.util.ResponseDataUtil;
import com.example.demo.util.aop.SystemLogAnnotation;
import com.example.demo.util.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Base64;
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
    @RequestMapping("sendMsgSuccess")
    @SystemLogAnnotation
    public Map sendMsgSuccess(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("UTF-8");

        String mobile = request.getParameter("mobilePhone");
        String content = "小助手抢票已完成，请尽快去12306官方进行车票支付。" +
                "温馨提示：开车前2小时以外的车票，须在30分钟内完成支付，开车前2小时以内的车票，须在10分钟内完成支付。" +
                "客服电话：19946187917【小助手】";
        String result = sendMessage(mobile,content);

        return ResponseDataUtil.responseData(result);
//        JSONObject resultJson = JSON.parseObject(result);

    }

    @RequestMapping("sendMsgCross")
    @SystemLogAnnotation
    public Map sendMsgCross(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("UTF-8");

        String mobile = request.getParameter("mobilePhone");
        String content = "您的票已完成跨站抢票，请尽快去12306官方进行车票支付。" +
                "温馨提示：开车前2小时以外的车票，须在30分钟内完成支付，开车前2小时以内的车票，须在10分钟内完成支付。" +
                "客服电话：19946187917【小助手】";
        String result = sendMessage(mobile,content);

        return ResponseDataUtil.responseData(result);
//        JSONObject resultJson = JSON.parseObject(result);

    }

    @RequestMapping("sendMsgConflict")
    @SystemLogAnnotation
    public Map sendMsgconFlict(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("UTF-8");

        String mobile = request.getParameter("mobilePhone");
        String content = "您的抢票订单与已有行程冲突，请确认行程重新下单。客服电话：19946187917【小助手】";
        String result = sendMessage(mobile,content);

        return ResponseDataUtil.responseData(result);
//        JSONObject resultJson = JSON.parseObject(result);

    }

    private String sendMessage(String mobile,String content)throws Exception{
        String username = "xiechengdp";
        String password = "lHlh1eE3";
        String xh = null;
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
        String utf8String = new String(url.getBytes(),"UTF-8");
        System.out.println(utf8String);
        String result = HttpUtil.postRequest(utf8String,jsonString,"UTF-8");
        System.out.println(result);
        return result;

    }

}
