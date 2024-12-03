package com.sczheng.controller;


import com.sczheng.pojo.PageBean;
import com.sczheng.pojo.Result;
import com.sczheng.pojo.ShoppingCart;
import com.sczheng.service.MyLogService;
import com.sczheng.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;




    //与前端交互，返回json数据
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize
                       ) {
        log.info("分页查询购物车信息:page={},size={}", page, pageSize);

        PageBean pageBean = shoppingCartService.page(page, pageSize);
        return Result.success(pageBean);
    }


    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {  //pathvariable注解，将路径中的参数绑定到方法的参数上
        log.info("删除购物车信息:ids={}", ids);
        shoppingCartService.delete(ids);
        return Result.success();
    }


    @PostMapping
    public Result insert(@RequestBody ShoppingCart shoppingCart) {
        log.info("添加购物车信息:{}", shoppingCart);
        shoppingCartService.insert(shoppingCart);
        return Result.success();
    }


    @PutMapping
    public Result update(@RequestBody ShoppingCart shoppingCart) {  //requestbody注解，将请求体中的json数据绑定到方法的参数上
        log.info("更新购物车信息:{}", shoppingCart);
        shoppingCartService.update(shoppingCart);
        return Result.success();
    }

    @GetMapping("/{userId}")
    public Result getCartById(@PathVariable Integer userId) {
        log.info("查询用户购物车信息:userId={}", userId);
        List<ShoppingCart> shoppingCarts = shoppingCartService.getCartById(userId);
        return Result.success(shoppingCarts);
    }


}
