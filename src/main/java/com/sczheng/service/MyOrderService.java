package com.sczheng.service;

import com.sczheng.pojo.PageBean;
import com.sczheng.pojo.MyOrder;

import java.util.List;

public interface MyOrderService {
    PageBean page(Integer page, Integer pageSize);
    
    

    void update(MyOrder myOrder);

    void insert(MyOrder myOrder);


    List<MyOrder> selectByUserId(Integer user_id);
}
