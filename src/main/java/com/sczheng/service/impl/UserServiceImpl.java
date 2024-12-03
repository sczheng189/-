package com.sczheng.service.impl;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sczheng.mapper.UserMapper;
import com.sczheng.pojo.PageBean;
import com.sczheng.pojo.User;
import com.sczheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String username) {
        //1设置分页参数
        PageHelper.startPage(page,pageSize);
        //2查询数据
        List<User> list = userMapper.list(username);
        //强转类型
        Page<User> p = (Page<User>) list;

        //3封装PageBean
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());


        return pageBean;
    }

    @Override
    public int delete(List<Integer> ids) {
        System.out.println(ids);
        userMapper.delete(ids);

        return userMapper.delete(ids);
    }



    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);

    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public void register(User user) {
        userMapper.register(user);

    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
