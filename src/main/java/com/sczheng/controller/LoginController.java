package com.sczheng.controller;

import com.sczheng.pojo.User;
import com.sczheng.pojo.Result;
import com.sczheng.service.UserService;
import com.sczheng.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("登录员工信息:{}", user);
        User e= userService.login(user);
        //登录成功,生成jwt令牌,下发令牌
        if (e!=null){
            //生成jwt令牌
            Map<String,Object> claims= new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());

            String jwt = JwtUtils.generateJwt(claims);

            //构造返回结果，包含更多信息
            Map<String, Object> result = new HashMap<>();
            result.put("token", jwt);
            result.put("role", e.getRole());

            return Result.success(result);
        }


        //登录失败
        return Result.error("用户名或密码错误");
    }


}
