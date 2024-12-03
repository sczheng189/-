package com.sczheng.service.impl;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sczheng.mapper.MyOrderMapper;
import com.sczheng.pojo.PageBean;
import com.sczheng.pojo.MyOrder;
import com.sczheng.service.MyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyOrderServiceImpl implements MyOrderService {

    
    @Autowired
    private MyOrderMapper myOrderMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        //1设置分页参数
        PageHelper.startPage(page,pageSize);
        //2查询数据
        List<MyOrder> list = myOrderMapper.list();
        //强转类型
        Page<MyOrder> p = (Page<MyOrder>) list;

        //3封装PageBean
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());


        return pageBean;
    }



    @Override
    public void update(MyOrder myOrder) {
        myOrderMapper.update(myOrder);

    }

    @Override
    public void insert(MyOrder myOrder) {
         myOrderMapper.insert(myOrder);
    }

    @Override
    public List<MyOrder> selectByUserId(Integer userId) {
        return myOrderMapper.selectByUserId(userId);
    }


}
