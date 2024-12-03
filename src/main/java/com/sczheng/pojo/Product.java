package com.sczheng.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product
{

    private Integer id;    //产品id
    private String name;   //产品名称
    private String description;  //产品描述
    private Double price;  //产品价格
    private String imageUrl;  //产品图片
    private Integer stockQuantity;  //库存数量
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间

}
