package com.sczheng.service.impl;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sczheng.mapper.MyLogMapper;
import com.sczheng.pojo.MyLog;
import com.sczheng.pojo.PageBean;
import com.sczheng.service.MyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MyLogServiceImpl implements MyLogService{

    
    @Autowired
    private MyLogMapper mylogMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        //1设置分页参数
        PageHelper.startPage(page,pageSize);
        //2查询数据
        List<MyLog> list = mylogMapper.list();
        //强转类型
        Page<MyLog> p = (Page<MyLog>) list;

        //3封装PageBean
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());


        return pageBean;
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, Short actionType) {
        //1设置分页参数
        PageHelper.startPage(page,pageSize);
        //2查询数据
        List<MyLog> list = mylogMapper.listWithAT(actionType);
        //强转类型
        Page<MyLog> p = (Page<MyLog>) list;

        //3封装PageBean
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());


        return pageBean;
    }
    
    
    @Override
    public List<MyLog> getByUserId(Integer userId) {
        return mylogMapper.getByUserId(userId);
    }


    @Override
    public void insert(MyLog mylog) {
         mylogMapper.insert(mylog);
    }

    
}
