package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminMapper
 * @Description TODO
 * @Author FeiChen
 * @Date 2018/12/18 17:03
 **/
@Mapper
@Component
public interface AdminMapper {

    List<Map> selectOrderList(@Param("date") String date, @Param("mobilePhone") String mobilePhone);

    int deleteOrderByid(@Param("id") int id);
}
