package com.sczheng.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//订单项类
public class User_Cart {
    private Integer id;         //订单项id
    private Integer userId;   //订单id
    private Integer cartId; //产品id
}

