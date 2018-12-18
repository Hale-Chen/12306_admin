package com.example.demo.service.impl;

import com.example.demo.mapper.AdminMapper;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminServiceImpl
 * @Description TODO
 * @Author FeiChen
 * @Date 2018/12/18 14:52
 **/
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public List<Map> selectOrderList(String date, String mobilePhone) {
        return adminMapper.selectOrderList(date,mobilePhone);
    }

    @Override
    public int deleteOrderByid(int id) {
        return adminMapper.deleteOrderByid(id);
    }
}
