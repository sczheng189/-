package com.sczheng.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyOrder {
    private Integer id;  // 订单id
    private Integer UserId; // 顾客id
    private Integer productId; // 商品id
    private LocalDateTime  orderDate; // 订单日期
    private Double totalAmount; // 订单总金额
    private Short finish; // 订单状态
}
