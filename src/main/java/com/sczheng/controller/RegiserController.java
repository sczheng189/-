package com.sczheng.controller;

import com.sczheng.pojo.Result;
import com.sczheng.pojo.User;
import com.sczheng.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RegiserController {


    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        log.info("注册用户信息:{}", user);
        try {
            userService.register(user);
            return Result.success();
        } catch (Exception e) {
            log.error("注册失败:", e);
            return Result.error("注册失败: " + e.getMessage());
        }
    }
}
