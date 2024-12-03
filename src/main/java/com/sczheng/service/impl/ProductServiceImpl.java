package com.sczheng.service.impl;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sczheng.mapper.ProductMapper;
import com.sczheng.pojo.PageBean;
import com.sczheng.pojo.Product;
import com.sczheng.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    
    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        //1设置分页参数
        PageHelper.startPage(page,pageSize);
        //2查询数据
        List<Product> list = productMapper.listAll();
        //强转类型
        Page<Product> p = (Page<Product>) list;

        //3封装PageBean
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());


        return pageBean;
    }


    @Override
    public PageBean page(Integer page, Integer pageSize, String name) {
        //1设置分页参数
        PageHelper.startPage(page,pageSize);
        //2查询数据
        List<Product> list = productMapper.list(name);
        //强转类型
        Page<Product> p = (Page<Product>) list;

        //3封装PageBean
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());


        return pageBean;
    }

    @Override
    public int delete(List<Integer> ids) {
        System.out.println(ids);
        productMapper.delete(ids);
        return productMapper.delete(ids);
    }



    @Override
    public Product getById(Integer id) {
        return productMapper.getById(id);
    }

    @Override
    public void update(Product product) {
        product.setUpdateTime(LocalDateTime.now());
        productMapper.update(product);
    }


    @Override
    public void insert(Product product) {
        product.setUpdateTime(LocalDateTime.now());
        product.setCreateTime(LocalDateTime.now());
        productMapper.insert(product);
    }

    @Override
    public void updateStock(Integer id, Integer decrementValue) {
        productMapper.updateStock(id,decrementValue);
    }




}
