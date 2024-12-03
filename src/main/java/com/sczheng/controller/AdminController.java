package com.sczheng.controller;


import com.sczheng.pojo.PageBean;
import com.sczheng.pojo.Result;
import com.sczheng.pojo.User;
import com.sczheng.service.MyLogService;
import com.sczheng.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin/userManage")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyLogService myLogService;


    //与前端交互，返回json数据
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String username) {
        log.info("分页查询用户信息:page={},size={} ,name={}", page, pageSize,username);

        PageBean pageBean = userService.page(page, pageSize,username);
        return Result.success(pageBean);
    }


    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {  //pathvariable注解，将路径中的参数绑定到方法的参数上
        log.info("删除用户信息:ids={}", ids);
        userService.delete(ids);
        return Result.success();
    }


    @PostMapping
    public Result save(@RequestBody User user) {
        log.info("添加用户信息:{}", user);
        userService.register(user);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询用户信息:id={}", id);
        User user = userService.getById(id);
        return Result.success(user);
    }


    @PutMapping
    public Result update(@RequestBody User user) {  //requestbody注解，将请求体中的json数据绑定到方法的参数上
        log.info("更新用户信息:{}", user);
        userService.update(user);
        return Result.success();
    }

    @GetMapping("/userlogs")
    public Result userlogs(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           HttpServletRequest request) {
        log.info("分页查询用户日志信息:page={},size={}", page, pageSize);
       PageBean pageBean = myLogService.page(page, pageSize);

        return Result.success(pageBean);
    }


}
