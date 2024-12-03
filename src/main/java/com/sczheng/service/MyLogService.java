package com.sczheng.service;

import com.sczheng.pojo.MyLog;
import com.sczheng.pojo.PageBean;

import java.time.LocalDateTime;
import java.util.List;

public interface MyLogService {
    PageBean page(Integer page, Integer pageSize);
    PageBean page(Integer page, Integer pageSize,Short actionType);
    
    void insert(MyLog mylog);
    
    List<MyLog> getByUserId(Integer userId);


    




}
