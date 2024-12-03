package com.sczheng.service;

import com.sczheng.pojo.PageBean;
import com.sczheng.pojo.User;


import java.time.LocalDate;
import java.util.List;

public interface UserService {
    PageBean page(Integer page, Integer pageSize , String username);

    int delete(List<Integer> ids);


    User getById(Integer id);

    void update(User user);

    User login(User user);

    void register(User user);


    User findByUsername(String username);
}
