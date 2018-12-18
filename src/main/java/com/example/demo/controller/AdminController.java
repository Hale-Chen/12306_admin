package com.example.demo.controller;

import com.example.demo.service.AdminService;
import com.example.demo.util.ResponseDataUtil;
import com.example.demo.util.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("selectOrderList")
    public Map selectOrderList(HttpServletRequest request, HttpServletResponse response){
        String mobilePhone = request.getParameter("mobilePhone");
        String date = request.getParameter("date");
        List<Map> result =  adminService.selectOrderList(date,mobilePhone);

        Map resultMap =  ResponseDataUtil.responseData(result);
        resultMap.put("total",result.size());
        return resultMap;
    }

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
}
