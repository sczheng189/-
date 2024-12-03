package com.sczheng.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id; //用户id
    private String username; //用户名
    private String email;  //邮箱
    private String password; //密码
    private String address;//地址
    private Double money; //余额
    private Short role; // 1 admin, 2 user  用户角色

//    private Short status; // 1 active,2 inactive  用户状态
//    private List<Log> browsingLogs; //浏览记录
//    private List<Order> purchaseHistory;  //购买记录
}
