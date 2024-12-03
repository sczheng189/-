package com.sczheng.controller;


import com.sczheng.pojo.PageBean;
import com.sczheng.pojo.Result;
import com.sczheng.pojo.MyLog;
import com.sczheng.service.MyLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/log")
public class MyLogController {

    @Autowired
    private MyLogService logService;


    //与前端交互，返回json数据
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       Short actionType
                       ) {
        log.info("分页查询用户日志信息:page={},size={},actionType={} ", page, pageSize,actionType);

        PageBean pageBean = logService.page(page, pageSize,actionType);
        return Result.success(pageBean);
    }



    @PostMapping
    public Result insert(@RequestBody MyLog mylog) {
        log.info("添加用户信息:{}", mylog);
        logService.insert(mylog);
        return Result.success();
    }

    @GetMapping("/{userId}")
    public Result getByUserId(@PathVariable Integer userId) {
        log.info("查询用户信息:id={}", userId);
        List<MyLog> myLog = logService.getByUserId(userId);
        return Result.success(myLog);
    }



}
