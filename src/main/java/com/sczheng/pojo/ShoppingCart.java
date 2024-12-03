package com.sczheng.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor


//购物车类
public class ShoppingCart {
    private Integer id;     //购物车id
    private Integer userId;     //用户id
    private Integer productId;   //产品id
    private Integer num;    //数量
    private Double totalPrice;   //价格
}

