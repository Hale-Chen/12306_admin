package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface AdminService {

    List<Map> selectOrderList(String date,String mobilePhone);

    int deleteOrderByid(int id);


}
