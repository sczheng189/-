package com.sczheng.service;

import com.sczheng.pojo.PageBean;
import com.sczheng.pojo.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    PageBean page(Integer page, Integer pageSize);

    int delete(List<Integer> ids);
    

    void update(ShoppingCart shoppingCart);

    void insert(ShoppingCart shoppingCart);


    List<ShoppingCart> getCartById(Integer userId);
}
