package com.sczheng.service;

import com.sczheng.pojo.PageBean;
import com.sczheng.pojo.Product;

import java.util.List;

public interface ProductService {

    PageBean page(Integer page, Integer pageSize);

    PageBean page(Integer page, Integer pageSize , String name);

    int delete(List<Integer> ids);


    Product getById(Integer id);

    void update(Product product);

    void insert(Product product);


    void updateStock(Integer id, Integer decrementValue);

}
