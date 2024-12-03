package com.sczheng.service.impl;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sczheng.mapper.ShoppingCartMapper;
import com.sczheng.pojo.PageBean;
import com.sczheng.pojo.ShoppingCart;
import com.sczheng.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        //1设置分页参数
        PageHelper.startPage(page,pageSize);
        //2查询数据
        List<ShoppingCart> list = shoppingCartMapper.list();
        //强转类型
        Page<ShoppingCart> p = (Page<ShoppingCart>) list;

        //3封装PageBean
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());


        return pageBean;
    }

    @Override
    public int delete(List<Integer> ids) {
        System.out.println(ids);
        shoppingCartMapper.delete(ids);

        return shoppingCartMapper.delete(ids);
    }



    @Override
    public void update(ShoppingCart shoppingCart) {
        shoppingCartMapper.update(shoppingCart);

    }

    @Override
    public void insert(ShoppingCart shoppingCart) {
         shoppingCartMapper.insert(shoppingCart);
    }

    @Override
    public List<ShoppingCart> getCartById(Integer userId) {
        return shoppingCartMapper.getCartById(userId);
    }


}
