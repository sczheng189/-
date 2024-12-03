package com.sczheng.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyLog {
    private Integer id;  //日志id
    private Integer userId;  //顾客id
    private Short actionType; // 0 browse, 1 purchase
    private Integer productId;  //产品id
    private LocalDateTime timestamp;  //时间
}

